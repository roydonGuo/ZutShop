package edu.zut.controller;


import edu.zut.domain.ResponseResult;
import edu.zut.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2022-12-15 20:04:09
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 分页查询当前登录用户的所有订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseResult selectAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return ResponseResult.okResult(orderService.userOrderList(pageNum, pageSize));
    }


}

