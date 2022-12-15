package edu.zut.controller;



import edu.zut.domain.ResponseResult;
import edu.zut.service.DictDistrictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DictDistrict)表控制层
 *
 * @author roydon
 * @since 2022-12-15 12:16:44
 */
@RestController
@RequestMapping("/district")
public class dictDistrictController {

    @Resource
    private DictDistrictService dictDistrictService;

    @GetMapping("/list")
    public ResponseResult selectAll() {
        return ResponseResult.okResult(dictDistrictService.allList());
    }

}

