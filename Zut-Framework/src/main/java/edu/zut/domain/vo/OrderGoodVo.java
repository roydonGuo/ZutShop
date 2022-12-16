package edu.zut.domain.vo;

import edu.zut.domain.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Author: roydon - 2022/12/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoodVo {

    private Integer oid;
    //归属于那个用户
    private Integer uid;
    //收货人
    private String name;
    //下单时间
    private Date orderTime;
    //集合
    private List<OrderItem> orderItemList;
}
