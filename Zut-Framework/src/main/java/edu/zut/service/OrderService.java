package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.dto.OrderDto;
import edu.zut.domain.entity.Order;


/**
 * (Order)表服务接口
 *
 * @author roydon
 * @since 2022-12-15 20:04:11
 */
public interface OrderService extends IService<Order> {

    ResponseResult userOrderList(Integer pageNum, Integer pageSize);

    Order getOrderByOid(Integer oid);

    Order createOrderByUser(OrderDto orderAddressGoodsDto);


}

