package edu.zut.domain.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (TRole)表实体类
 *
 * @author makejava
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
    private String flag;


}

