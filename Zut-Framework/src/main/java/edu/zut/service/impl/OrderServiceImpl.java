package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Order;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import edu.zut.mapper.OrderMapper;
import edu.zut.service.OrderService;
import edu.zut.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * (TOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-12-15 20:04:11
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Override
    public ResponseResult userOrderList(Integer pageNum, Integer pageSize) {

        //取出登录用户的id
        Integer userId = SecurityUtils.getUserId();
        if (Objects.isNull(userId)) {
            //没有携带token
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUid, userId);

        Page<Order> page = page(new Page<>(pageNum, pageSize), queryWrapper);

        return ResponseResult.okResult(page);

    }
}

