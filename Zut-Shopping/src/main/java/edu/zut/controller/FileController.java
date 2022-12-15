package edu.zut.controller;

import edu.zut.domain.ResponseResult;
import edu.zut.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Author: roydon - 2022/12/14
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam MultipartFile file) {
        //头像上传
        return ResponseResult.okResult(fileService.uploadImg(file));
    }

}