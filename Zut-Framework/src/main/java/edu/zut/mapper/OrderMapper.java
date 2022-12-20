package edu.zut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zut.domain.entity.Order;


/**
 * (Order)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-15 20:04:11
 */
public interface OrderMapper extends BaseMapper<Order> {

    Integer createOrderReturnId(Order order);
}

