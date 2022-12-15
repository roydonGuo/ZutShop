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
 * (TAddress)表实体类
 *
 * @author makejava
 * @since 2022-12-14 22:08:23
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_address")
public class Address  {
    //收货地址id@TableId
    @TableId(value = "aid")
    private Integer aid;
    //所属用户的id
    private Integer uid;
    //收货人姓名
    private String name;
    //省的代号
    private String province;
    //市的代号
    private String city;
    //区的代号
    private String area;
    //省市区的名称
    private String district;
    //邮政编码
    private String zip;
    //详细地址
    private String address;
    //手机
    private String phone;
    //固话
    private String tel;
    //地址类型，如：家或学校
    private String tag;
    //是否默认，0表示非默认 1-默认
    private Integer isDefault;
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

