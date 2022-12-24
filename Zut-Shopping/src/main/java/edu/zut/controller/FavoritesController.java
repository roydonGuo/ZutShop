package edu.zut.controller;


import edu.zut.domain.ResponseResult;
import edu.zut.service.FavoritesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Favorites)表控制层
 *
 * @author roydon
 * @since 2022-12-19 19:23:03
 */
@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Resource
    private FavoritesService favoritesService;

    /**
     * 添加商品收藏
     *
     * @param gid 商品gid
     * @return boolean
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Integer gid) {
        return ResponseResult.okResult(favoritesService.addFavorites(gid));
    }

    /**
     * 收藏商品列表
     *
     * @param pageNum 页号
     * @param pageSize 大小
     * @return Page<Goods>
     */
    @GetMapping("/list")
    public ResponseResult selectAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return ResponseResult.okResult(favoritesService.favoritesList(pageNum, pageSize));
    }


}

