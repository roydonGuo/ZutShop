package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Cart;
import edu.zut.domain.entity.Goods;
import edu.zut.domain.vo.CartGoodsVo;
import edu.zut.exception.SystemException;
import edu.zut.mapper.CartMapper;
import edu.zut.service.CartService;
import edu.zut.service.GoodsService;
import edu.zut.utils.BeanCopyUtils;
import edu.zut.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static edu.zut.enums.AppHttpCodeEnum.NEED_LOGIN;

/**
 * (Cart)表服务实现类
 *
 * @author roydon
 * @since 2022-12-17 00:18:01
 */
@Slf4j
@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private GoodsService goodsService;

    @Override
    public List<CartGoodsVo> userCartGoodList(Integer uid) {

        //先根据用户id查询购物车数据
        LambdaQueryWrapper<Cart> cartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cartLambdaQueryWrapper.eq(Cart::getUid, uid);
        cartLambdaQueryWrapper.orderByDesc(Cart::getCreatedTime);
        List<Cart> cartList = list(cartLambdaQueryWrapper);
        //封装VO
        List<CartGoodsVo> cartGoodsVoList = new ArrayList<>();
        cartList.forEach(c -> {
            CartGoodsVo cartGoodsVo = BeanCopyUtils.copyBean(c, CartGoodsVo.class);
            //商品id
            Integer gid = c.getGid();
            LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
            goodsLambdaQueryWrapper.eq(Goods::getGid, gid);
            // TODO 待添加此商品是否被下架逻辑
            Goods goods = goodsService.getOne(goodsLambdaQueryWrapper);
            cartGoodsVo.setGoods(goods);
            cartGoodsVoList.add(cartGoodsVo);
        });
        return cartGoodsVoList;
    }

    @Override
    public boolean removeCartGoodByCid(Integer cid) {
        LambdaQueryWrapper<Cart> cartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cartLambdaQueryWrapper.eq(Cart::getCid, cid);
        boolean remove = remove(cartLambdaQueryWrapper);

        return remove;
    }

    @Override
    public ResponseResult addCartByUid(Cart cart) {

        //取出登录用户的id
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
        cart.setUid(userId);
        if(cart.getNum()==0){
            cart.setNum(1);
        }
        //如果购物车有了此商品，就更新
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUid, userId);
        queryWrapper.eq(Cart::getGid, cart.getGid());

        log.info("待加入购物车数据==>{}", cart);

        saveOrUpdate(cart,queryWrapper);

        return ResponseResult.okResult();
    }
}

