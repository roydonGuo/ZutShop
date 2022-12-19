package edu.zut.controller;


import edu.zut.domain.ResponseResult;
import edu.zut.service.FavoritesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Favorites)表控制层
 *
 * @author roydon
 * @since 2022-12-19 19:23:03
 */
@RestController
@RequestMapping("/favorites")
public class FavoritesController  {

    @Resource
    private FavoritesService favoritesService;

    /**
     * 收藏商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseResult selectAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return ResponseResult.okResult(favoritesService.favoritesList(pageNum, pageSize));
    }

}

