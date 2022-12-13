package edu.zut.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: roydon - 2022/12/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private String token;
    private UserInfoVo userInfo;
}
