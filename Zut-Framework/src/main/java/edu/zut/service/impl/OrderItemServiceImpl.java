package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Order;
import edu.zut.domain.entity.OrderItem;
import edu.zut.mapper.OrderItemMapper;
import edu.zut.service.OrderItemService;
import edu.zut.service.OrderService;
import edu.zut.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OrderItem)表服务实现类
 *
 * @author roydon
 * @since 2022-12-15 22:21:48
 */
@Slf4j
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Resource
    private OrderService orderService;

    @Override
    @Transactional
    public ResponseResult delOrderItem(OrderItem orderItem) {
        log.info("订单商品==>{}",orderItem);
        LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderItemLambdaQueryWrapper.eq(OrderItem::getGid, orderItem.getGid());
        orderItemLambdaQueryWrapper.eq(OrderItem::getOid, orderItem.getOid());
        orderItemLambdaQueryWrapper.eq(OrderItem::getCreatedUser, SecurityUtils.getUserId());
        boolean remove = remove(orderItemLambdaQueryWrapper);
        //删除了订单中的商品，判断订单是否还有商品，没有则删除
        if (remove) {
            LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(OrderItem::getOid, orderItem.getOid());
            wrapper.eq(OrderItem::getCreatedUser, SecurityUtils.getUserId());
            List<OrderItem> orderList = list(wrapper);
            //此订单已经没有商品
            if (orderList.size() == 0) {
                //删除订单
                LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
                orderLambdaQueryWrapper.eq(Order::getOid, orderItem.getOid());
                orderLambdaQueryWrapper.eq(Order::getUid, SecurityUtils.getUserId());
                boolean remove2 = orderService.remove(orderLambdaQueryWrapper);
            }
        }

        return ResponseResult.okResult();
    }
}

