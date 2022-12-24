package edu.zut.domain.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (Role)表实体类
 *
 * @author roydon
 * @since 2022-12-21 23:14:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role")
public class Role {
    //权限id@TableId
    @TableId(value = "rid")
    private Integer rid;

    //权限名
    private String name;
    //权限描述
    private String description;
    //权限
    private String role;
    private Integer status;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;


}

