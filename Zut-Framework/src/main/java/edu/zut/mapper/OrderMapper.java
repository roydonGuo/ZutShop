package edu.zut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zut.domain.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;


/**
 * (Order)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-15 20:04:11
 */
public interface OrderMapper extends BaseMapper<Order> {

    Integer createOrderReturnId(Order order);

    @Update("update t_order set status=#{status},pay_time=#{payTime},,alipay_no=#{alipayNo} where oid = #{oid}")
    void updateState(@Param("oid") String oid, @Param("status") Integer status, @Param("payTime")Date payTime, @Param("alipayNo")String alipayNo);

}

