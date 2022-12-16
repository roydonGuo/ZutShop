package edu.zut.controller;

import edu.zut.domain.ResponseResult;
import edu.zut.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param title 商品名
     * @return ResponseResult
     */
    @GetMapping("/search")
    public ResponseResult searchGood(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam String title){
        return ResponseResult.okResult(goodsService.searchGoodListByTitle(pageNum, pageSize,title));

    }

}

