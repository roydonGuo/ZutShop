package edu.zut.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
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
    @TableId(value = "uid")
    private Integer uid;

    //用户名
    @ApiModelProperty(value = "用户名")
    private String username;
    //密码
    private String password;
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
    @TableField(fill = FieldFill.INSERT)
    private Integer createdUser;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    //修改执行人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer modifiedUser;
    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;


}

