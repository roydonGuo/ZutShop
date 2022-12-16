package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Order;
import edu.zut.domain.entity.OrderItem;
import edu.zut.domain.vo.OrderGoodVo;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import edu.zut.mapper.OrderMapper;
import edu.zut.service.OrderItemService;
import edu.zut.service.OrderService;
import edu.zut.utils.BeanCopyUtils;
import edu.zut.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * (Order)表服务实现类
 *
 * @author roydon
 * @since 2022-12-15 20:04:11
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderItemService orderItemService;

    @Override
    public ResponseResult userOrderList(Integer pageNum, Integer pageSize) {

        //取出登录用户的id
        Integer userId = SecurityUtils.getUserId();
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
        orderList.forEach(o->{
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
        queryWrapper.eq(Order::getOid,oid);
        Order order = getOne(queryWrapper);

        return order;
    }
}

