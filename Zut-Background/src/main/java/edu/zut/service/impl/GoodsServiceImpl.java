package edu.zut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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


}

