package edu.zut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.OrderItem;
import edu.zut.mapper.OrderItemMapper;
import edu.zut.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * (OrderItem)表服务实现类
 *
 * @author roydon
 * @since 2022-12-15 22:21:48
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}

