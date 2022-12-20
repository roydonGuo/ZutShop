package edu.zut.domain.dto;

import edu.zut.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    //主键
    private Integer oid;
    //归属用户的id
    private Integer uid;
    //地址id
    private Integer aid;
    //总价
    private Integer price;
    //商品集合
    private List<Cart> cartList;

}