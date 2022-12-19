package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.Favorites;
import edu.zut.domain.entity.Goods;
import edu.zut.exception.SystemException;
import edu.zut.mapper.FavoritesMapper;
import edu.zut.service.FavoritesService;
import edu.zut.service.GoodsService;
import edu.zut.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static edu.zut.enums.AppHttpCodeEnum.NEED_LOGIN;

/**
 * (Favorites)表服务实现类
 *
 * @author roydon
 * @since 2022-12-19 19:23:04
 */
@Service("favoritesService")
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {

    @Resource
    private GoodsService goodsService;

    @Override
    @Transactional
    public Page<Goods> favoritesList(Integer pageNum, Integer pageSize) {

        //取出登录用户的id
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
        LambdaQueryWrapper<Favorites> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favorites::getUid, userId);
        //按修改时间降序排序
        Page<Favorites> favoritesPage = page(new Page<>(pageNum, pageSize), queryWrapper);
        List<Favorites> favoritesList = favoritesPage.getRecords();
        //遍历封装goods
        List<Goods> goodsSet = new ArrayList<>();
        favoritesList.forEach(f -> {
            //商品id
            Integer gid = f.getGid();
            Goods goods = goodsService.getByGid(gid);
            goodsSet.add(goods);
        });
        Page<Goods> goodsPage = new Page<>();;
        goodsPage.setCurrent(favoritesPage.getCurrent());
        goodsPage.setPages(favoritesPage.getPages());
        goodsPage.setSize(favoritesPage.getSize());
        goodsPage.setTotal(favoritesPage.getTotal());
        goodsPage.setRecords(goodsSet);
        //TODO 将查询出的商品保存到redis
        return (goodsPage);


    }
}

