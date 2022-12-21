package edu.zut.controller;

import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.OrderItem;
import edu.zut.service.OrderItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 订单中的商品已签收
     *
     * @param orderItem
     * @return
     */
    @PostMapping("/receive")
    public ResponseResult remove(@RequestBody OrderItem orderItem) {

        return ResponseResult.okResult(orderItemService.delOrderItem(orderItem));

    }

}

