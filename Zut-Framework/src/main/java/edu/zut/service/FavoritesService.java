package edu.zut.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.entity.Favorites;
import edu.zut.domain.entity.Goods;


/**
 * (Favorites)表服务接口
 *
 * @author roydon
 * @since 2022-12-19 19:23:04
 */
public interface FavoritesService extends IService<Favorites> {

    Page<Goods> favoritesList(Integer pageNum, Integer pageSize);
}

