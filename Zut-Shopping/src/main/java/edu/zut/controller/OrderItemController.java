package edu.zut.controller;

import edu.zut.service.OrderItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * (OrderItem)表控制层
 *
 * @author roydon
 * @since 2022-12-15 22:21:47
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Resource
    private OrderItemService orderItemService;


}

