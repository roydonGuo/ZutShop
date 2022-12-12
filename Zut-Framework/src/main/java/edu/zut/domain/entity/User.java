package edu.zut.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * (TUser)表实体类
 *
 * @author makejava
 * @since 2022-12-12 10:08:30
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
    //用户id@TableId
    private Integer uid;

    //用户名
    private String username;
    //密码
    private String password;
    //盐值
    private String salt;
    //性别,0-女,1-男
    private Integer gender;
    //电话
    private String phone;
    //邮箱
    private String email;
    //头像
    private String avatar;
    //是否删除,0-未删除,1-已删除
    private Integer isDelete;
    //创建执行人
    private String createdUser;
    //创建时间
    private Date createdTime;
    //修改执行人
    private String modifiedUser;
    //修改时间
    private Date modifiedTime;


}

