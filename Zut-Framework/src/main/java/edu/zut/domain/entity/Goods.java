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
 * (TGoods)表实体类
 *
 * @author makejava
 * @since 2022-12-15 21:38:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_goods")
public class Goods {
    //商品id@TableId
    @TableId(value = "gid")
    private Integer gid;
    //分类id
    private Integer categoryId;
    //商品系列
    private String itemType;
    //商品标题
    private String title;
    //商品卖点
    private String sellPoint;
    //商品单价
    private Long price;
    //库存数量
    private Integer num;
    //条形码
    private String barcode;
    //图片路径
    private String image;
    //商品状态  1：上架   2：下架   3：删除
    private Integer status;
    //显示优先级
    private Integer priority;
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

