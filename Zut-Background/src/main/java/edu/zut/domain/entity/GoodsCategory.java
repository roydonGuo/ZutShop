package edu.zut.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (GoodsCategory)表实体类
 *
 * @author roydon
 * @since 2022-12-24 19:06:33
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_goods_category")
public class GoodsCategory  {
    //主键@TableId
    @TableId(value = "id")
    private Integer id;

    //父分类id
    private Integer parentId;
    //名称
    private String name;
    //状态   1：正常   0：删除
    private Integer status;
    //排序号
    private Integer sortOrder;
    //是否是父分类   1：是  0：否
    private Integer isParent;
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

    //子分类
    @TableField(exist = false)
    private List<GoodsCategory> chileList = new ArrayList<>();


}

