package edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Goods;
import edu.zut.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Goods)表控制层
 *
 * @author roydon
 * @since 2022-12-15 21:38:32
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 分页查询所有商品【暂行方案】
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseResult selectAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return ResponseResult.okResult(goodsService.goodList(pageNum, pageSize));
    }

    /**
     * 根据商品名模糊搜索,并分页
     *
     * @param title 商品名
     * @return ResponseResult
     */
    @GetMapping("/search")
    public ResponseResult searchGood(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam String title) {
        return ResponseResult.okResult(goodsService.searchGoodListByTitle(pageNum, pageSize, title));

    }

    /**
     * 今日热销
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/today")
    public ResponseResult todayGood(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize
    ) {
        return ResponseResult.okResult(goodsService.todayGoodList(pageNum, pageSize));

    }

    /**
     * 根据gid查询商品
     *
     * @param gid
     * @return
     */
    @GetMapping("{gid}")
    public ResponseResult getGoods(@PathVariable Integer gid) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getGid, gid);
        return ResponseResult.okResult(goodsService.getOne(queryWrapper));
    }


}

