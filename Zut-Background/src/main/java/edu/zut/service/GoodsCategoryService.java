package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.entity.GoodsCategory;

import java.util.List;


/**
 * (GoodsCategory)表服务接口
 *
 * @author roydon
 * @since 2022-12-24 19:06:34
 */
public interface GoodsCategoryService extends IService<GoodsCategory> {

    List<GoodsCategory> getListTree();
}

