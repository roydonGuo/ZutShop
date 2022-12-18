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
 * (Cart)表实体类
 *
 * @author roydon
 * @since 2022-12-17 00:18:00
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_cart")
public class Cart {
    //数据id@TableId
    @TableId(value = "cid")
    private Integer cid;

    //归属用户的id
    private Integer uid;
    //商品的id
    private Integer gid;
    //商品的数量
    private Integer num;
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

