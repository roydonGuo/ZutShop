package edu.zut.controller;


import edu.zut.service.GoodsCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (GoodsCategory)表控制层
 *
 * @author roydon
 * @since 2022-12-24 19:06:32
 */
@RestController
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;



}