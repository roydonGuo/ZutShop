package edu.zut.service;

import edu.zut.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 */
public interface FileService {

    ResponseResult uploadImg(MultipartFile file);

}
