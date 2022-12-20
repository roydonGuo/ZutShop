package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.dto.OrderDto;
import edu.zut.domain.entity.*;
import edu.zut.domain.vo.OrderGoodVo;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import edu.zut.mapper.OrderMapper;
import edu.zut.service.*;
import edu.zut.utils.BeanCopyUtils;
import edu.zut.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static edu.zut.constants.SystemConstants.ORDER_CREATED;
import static edu.zut.enums.AppHttpCodeEnum.NEED_LOGIN;
import static edu.zut.enums.AppHttpCodeEnum.ORDER_CANT_CREATE;

/**
 * (Order)表服务实现类
 *
 * @author roydon
 * @since 2022-12-15 20:04:11
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderItemService orderItemService;

    @Override
    public ResponseResult userOrderList(Integer pageNum, Integer pageSize) {

        //取出登录用户的id
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
        if (Objects.isNull(userId)) {
            //没有携带token
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUid, userId);
        Page<Order> pageOrder = new Page<>(pageNum, pageSize);

        Page<Order> page = page(pageOrder, queryWrapper);

        List<Order> orderList = page.getRecords();
        //TODO 将订单包含的商品order_item封装进order
        List<OrderGoodVo> orderGoodVoList = new ArrayList<>();
        orderList.forEach(o -> {
            //订单od
            Integer oid = o.getOid();
            //根据订单id查询order_item集合
            LambdaQueryWrapper<OrderItem> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(OrderItem::getOid, oid);
            List<OrderItem> orderItemList = orderItemService.list(queryWrapper2);
            OrderGoodVo orderGoodVo = BeanCopyUtils.copyBean(o, OrderGoodVo.class);
            orderGoodVo.setOrderItemList(orderItemList);
            orderGoodVoList.add(orderGoodVo);
        });

        Page<OrderGoodVo> orderGoodVoPage = new Page<>();
        orderGoodVoPage.setCurrent(page.getCurrent());
        orderGoodVoPage.setPages(page.getPages());
        orderGoodVoPage.setSize(page.getSize());
        orderGoodVoPage.setTotal(page.getTotal());
        orderGoodVoPage.setRecords(orderGoodVoList);

        return ResponseResult.okResult(orderGoodVoPage);

    }

    @Override
    public Order getOrderByOid(Integer oid) {

        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOid, oid);
        Order order = getOne(queryWrapper);

        return order;
    }

    @Resource
    private AddressService addressService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private CartService cartService;

    @Resource
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public ResponseResult createOrderByUser(OrderDto orderDto) {

        log.info("购物车结算生成订单数据==>{}", orderDto);
        //(OrderDto(oid=null, uid=null, aid=21, price=39811,
        // cartList=[
        // Cart(cid=30, uid=1, gid=10000042, num=4, createdUser=null, createdTime=Mon Dec 19 22:33:20 CST 2022, modifiedUser=null, modifiedTime=null),
        // Cart(cid=31, uid=1, gid=100000401, num=5, createdUser=null, createdTime=Mon Dec 19 20:14:53 CST 2022, modifiedUser=null, modifiedTime=null)
        // ])
        //取出登录用户的id
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
        if (Objects.isNull(userId)) {
            //没有携带token
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        //TODO 0.查询收货地址
        LambdaQueryWrapper<Address> addressQueryWrapper = new LambdaQueryWrapper<>();
        addressQueryWrapper.eq(Address::getUid, userId);
        addressQueryWrapper.eq(Address::getAid, orderDto.getAid());
        Address address = addressService.getOne(addressQueryWrapper);
        if (address == null) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
        //TODO 1.创建订单：uid，address.name/phone/address ,price
        Order order = new Order();
        order.setUid(userId);
        order.setName(address.getName());
        order.setPhone(address.getPhone());
        order.setAddress(address.getAddress());
        order.setPrice(orderDto.getPrice());
        order.setStatus(ORDER_CREATED);
        order.setOrderTime(new Date());
        Integer rows = orderMapper.createOrderReturnId(order);
        if (rows < 1) {
            throw new SystemException(ORDER_CANT_CREATE);
        }
        Integer oid = order.getOid();
        log.info("插入成功后自增id==>{}", oid);
        //TODO 2.创建了订单，操作表order_item添加商品与订单关联
        //cartList=[
        //Cart(cid=30, uid=1, gid=10000042, num=4, createdUser=null, createdTime=Mon Dec 19 22:33:20 CST 2022, modifiedUser=null, modifiedTime=null),
        //Cart(cid=31, uid=1, gid=100000401, num=5, createdUser=null, createdTime=Mon Dec 19 20:14:53 CST 2022, modifiedUser=null, modifiedTime=null)
        //]
        List<Cart> cartList = orderDto.getCartList();
        cartList.forEach(c -> {
            //TODO 2.1 查询购物车的商品
            LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
            goodsLambdaQueryWrapper.eq(Goods::getGid, c.getGid());
            Goods goods = goodsService.getOne(goodsLambdaQueryWrapper);
            log.info("商品数据==>{}", goods);
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(oid);
            orderItem.setGid(c.getGid());
            orderItem.setTitle(goods.getTitle());
            orderItem.setImage(goods.getImage());
            orderItem.setPrice(goods.getPrice());
            orderItem.setNum(c.getNum());
            orderItemService.save(orderItem);
            //TODO 2.2 删除购物车该商品
            LambdaQueryWrapper<Cart> cartQueryWrapper = new LambdaQueryWrapper<>();
            cartQueryWrapper.eq(Cart::getGid, c.getGid());
            cartService.remove(cartQueryWrapper);
        });

        return ResponseResult.okResult();
    }
}

