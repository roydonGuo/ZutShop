package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.OrderItem;


/**
 * (OrderItem)表服务接口
 *
 * @author roydon
 * @since 2022-12-15 22:21:48
 */
public interface OrderItemService extends IService<OrderItem> {

    ResponseResult delOrderItem(OrderItem orderItem);
}

