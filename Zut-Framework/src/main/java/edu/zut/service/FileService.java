package edu.zut.service;

import edu.zut.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ResponseResult uploadImg(MultipartFile file);

}
