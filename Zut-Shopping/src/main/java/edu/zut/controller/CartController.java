package edu.zut.controller;

import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Cart;
import edu.zut.service.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Cart)表控制层
 *
 * @author roydon
 * @since 2022-12-17 00:17:58
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 用户购物车数据
     *
     * @param uid 用户uid
     * @return List<CartGoodsVo>
     */
    @RequestMapping("/list")
    public ResponseResult userCart(@RequestParam Integer uid) {
        return ResponseResult.okResult(cartService.userCartGoodList(uid));
    }

    /**
     * 根据购物车id删除购物车
     *
     * @param cid 购物车cid
     * @return boolean
     */
    @DeleteMapping("/{cid}")
    public ResponseResult deleteCart(@PathVariable Integer cid) {
        return ResponseResult.okResult(cartService.removeCartGoodByCid(cid));
    }

    /**
     * 添加购物车
     *
     * @param cart cart
     * @return okResult
     */
    @PostMapping("/add")
    public ResponseResult addCart(@RequestBody Cart cart) {
        return ResponseResult.okResult(cartService.addCartByUid(cart));
    }


}

