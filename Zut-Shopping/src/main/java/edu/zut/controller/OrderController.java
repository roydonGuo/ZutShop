package edu.zut.controller;


import edu.zut.domain.ResponseResult;
import edu.zut.domain.vo.CartGoodsVo;
import edu.zut.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     *
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return ResponseResult
     */
    @GetMapping("/list")
    public ResponseResult selectAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return ResponseResult.okResult(orderService.userOrderList(pageNum, pageSize));
    }

    /**
     * 根据订单id获取订单
     *
     * @param oid oid
     * @return Order
     */
    @GetMapping("/{oid}")
    public ResponseResult getOrder(@PathVariable Integer oid) {
        return ResponseResult.okResult(orderService.getOrderByOid(oid));
    }


    /**
     * 用户在购物车选择商品数据，生成订单
     *
     * @param cartGoodsVoList List<CartGoodsVo>
     * @return ResponseResult
     */
    @PostMapping("/create")
    public ResponseResult createOrder(@RequestBody List<CartGoodsVo> cartGoodsVoList) {
        return ResponseResult.okResult(orderService.createOrderByUser(cartGoodsVoList));
    }


}

