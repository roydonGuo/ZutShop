package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Goods;
import edu.zut.mapper.GoodsMapper;
import edu.zut.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * (Goods)表服务实现类
 *
 * @author roydon
 * @since 2022-12-15 21:38:34
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public ResponseResult goodList(Integer pageNum, Integer pageSize) {

//        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();

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

        //TODO 后续es做

        return goodsPage;
    }
}

