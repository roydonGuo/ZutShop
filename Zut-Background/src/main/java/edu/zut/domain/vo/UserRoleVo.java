package edu.zut.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import edu.zut.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Author: roydon - 2022/12/22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVo {
    private Integer uid;

    //用户名
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

    private List<Role> roleList;

}
