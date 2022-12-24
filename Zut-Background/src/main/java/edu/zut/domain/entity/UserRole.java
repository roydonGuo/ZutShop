package edu.zut.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (UserRole)表实体类
 *
 * @author roydon
 * @since 2022-12-23 21:41:39
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_role")
public class UserRole {
    //用户id@TableId
    private Integer id;

    private Integer uid;
    //用户具有的权限id@TableId
    private Integer rid;

}

