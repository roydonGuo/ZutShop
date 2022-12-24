package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Goods;
import edu.zut.mapper.GoodsMapper;
import edu.zut.service.GoodsService;
import edu.zut.utils.RedisCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static edu.zut.constants.RedisConstants.TODAY_GOODS_KEY;
import static edu.zut.constants.RedisConstants.TODAY_GOODS_TTL;

/**
 * (Goods)表服务实现类
 *
 * @author roydon
 * @since 2022-12-15 21:38:34
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult goodList(Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        //按优先级排序
        queryWrapper.orderByDesc(Goods::getPriority);
        Page<Goods> page = page(new Page<>(pageNum, pageSize));

        return ResponseResult.okResult(page);

    }

    @Override
    public Page<Goods> searchGoodListByTitle(Integer pageNum, Integer pageSize, String title) {

        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Goods::getTitle, title);
        //按优先级排序
        queryWrapper.orderByDesc(Goods::getPriority);

        Page<Goods> goodsPage = page(new Page<>(pageNum, pageSize), queryWrapper);

        return goodsPage;
    }

    @Override
    public Page<Goods> todayGoodList(Integer pageNum, Integer pageSize) {
        //查询redis
        Page<Goods> todayGoodsList = redisCache.getCacheObject(TODAY_GOODS_KEY + pageNum);
        if (todayGoodsList == null) {
            LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
            //按创建时间倒序排序
            queryWrapper.orderByDesc(Goods::getCreatedTime);
            Page<Goods> goodsPage = page(new Page<>(pageNum, pageSize), queryWrapper);
            //TODO 存入redis
            redisCache.setCacheObject(TODAY_GOODS_KEY + pageNum, goodsPage, TODAY_GOODS_TTL, TimeUnit.SECONDS);
            return goodsPage;
        } else {
            return todayGoodsList;
        }
    }

    /**
     * 根据商品id查询商品
     *
     * @param gid
     * @return
     */
    @Override
    public Goods getByGid(Integer gid) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getGid,gid);
        Goods goods = getOne(queryWrapper);
        return goods;
    }
}

