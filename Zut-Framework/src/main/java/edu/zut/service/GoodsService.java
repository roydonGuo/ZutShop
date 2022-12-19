package edu.zut.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Goods;


/**
 * (Goods)表服务接口
 *
 * @author roydon
 * @since 2022-12-15 21:38:33
 */
public interface GoodsService extends IService<Goods> {

    ResponseResult goodList(Integer pageNum, Integer pageSize);

    Page<Goods> searchGoodListByTitle(Integer pageNum, Integer pageSize, String title);

    Page<Goods> todayGoodList(Integer pageNum, Integer pageSize);

    Goods getByGid(Integer gid);
}

