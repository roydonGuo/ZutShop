package edu.zut.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * (Order)表实体类
 *
 * @author makejava
 * @since 2022-12-15 20:04:11
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class Order {
    //id@TableId
    @TableId(value = "oid")
    private Integer oid;
    //归属于那个用户
    private Integer uid;
    //收货人
    private String name;
    //收货电话
    private String phone;
    //收货地址
    private String address;
    //订单状态 0-未支付，1-已支付，2-已取消，3-未发货
    private Integer status;
    //商品总价
    private Integer price;
    //支付宝流水号
    private String alipayNo;
    //下单时间
    @TableField(fill = FieldFill.INSERT)
    private Date orderTime;
    //支付时间
    private String payTime;
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

