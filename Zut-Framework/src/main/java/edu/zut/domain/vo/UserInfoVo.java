package edu.zut.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author: roydon - 2022/12/12
 **/

@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;

    private Integer sex;

    private String email;


}