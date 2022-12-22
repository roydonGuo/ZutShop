package edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Goods;
import edu.zut.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     * 分页查询所有商品
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public ResponseResult selectAll(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String title) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Goods::getTitle,title);
        lambdaQueryWrapper.orderByDesc(Goods::getPriority);
        return ResponseResult.okResult(goodsService.page(new Page<>(pageNum, pageSize),lambdaQueryWrapper));
    }

    /**
     * 新增或者更新
     *
     * @param goods
     * @return
     */
    @PostMapping
    public ResponseResult addUser(@RequestBody Goods goods) {
        return ResponseResult.okResult(goodsService.saveOrUpdate(goods));
    }

    /**
     * 根据gid删除
     *
     * @param gid
     * @return
     */
    @DeleteMapping("/del/{gid}")
    public ResponseResult deleteUser(@PathVariable Integer gid) {
        return ResponseResult.okResult(goodsService.removeById(gid));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        return ResponseResult.okResult(goodsService.removeByIds(ids));
    }

}

