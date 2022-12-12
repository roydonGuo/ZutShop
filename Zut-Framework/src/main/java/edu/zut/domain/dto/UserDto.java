package edu.zut.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: roydon - 2022/12/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "注册用户dto")
public class UserDto {

    //用户名
    private String username;
    //密码
    private String password;


}
