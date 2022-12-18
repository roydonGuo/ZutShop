package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Cart;
import edu.zut.domain.vo.CartGoodsVo;

import java.util.List;


/**
 * (Cart)表服务接口
 *
 * @author roydon
 * @since 2022-12-17 00:18:01
 */
public interface CartService extends IService<Cart> {

    List<CartGoodsVo> userCartGoodList(Integer uid);

    boolean removeCartGoodByCid(Integer cid);

    ResponseResult addCartByUid(Cart cart);
}

