package edu.zut.domain.dto;

import edu.zut.domain.entity.Address;
import edu.zut.domain.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddressGoodsDto {
    private Integer cid;
    //归属用户的id
    private Integer uid;
    //商品id
    private Integer gid;
    //商品的数量
    private Integer num;
    //创建时间
    private Date createdTime;
    //商品
    private Goods goods;
    //收货地址
    private Address address;

}