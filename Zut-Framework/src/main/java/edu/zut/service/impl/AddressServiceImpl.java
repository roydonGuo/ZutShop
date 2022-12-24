package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Address;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import edu.zut.mapper.AddressMapper;
import edu.zut.service.AddressService;
import edu.zut.utils.RedisCache;
import edu.zut.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static edu.zut.constants.SystemConstants.IS_DEFAULT;
import static edu.zut.constants.SystemConstants.NOT_DEFAULT;
import static edu.zut.enums.AppHttpCodeEnum.NEED_LOGIN;

/**
 * (Address)表服务实现类
 *
 * @author roydon
 * @since 2022-12-14 22:08:25
 */
@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult userAddressList(Integer pageNum, Integer pageSize) {
        //取出登录用户的id
        Integer userId = SecurityUtils.getUserId();
        if (Objects.isNull(userId)) {
            //没有携带token
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUid, userId);

        Page<Address> page = page(new Page<>(pageNum, pageSize), queryWrapper);

        return ResponseResult.okResult(page);
    }

    @Override
    public ResponseResult addAddress(Address address) {

        //取出登录用户的id
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUid, userId);
        //如果新增为第一条地址数据，则设为默认
        if (count(queryWrapper) < 1) {
            address.setIsDefault(IS_DEFAULT);
        }
        address.setUid(SecurityUtils.getUserId());
        save(address);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateAddress(Address address) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getAid, address.getAid());
        update(address, queryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult setDefaultAddress(Address address) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();

        //地址默认改为非默认
        if (address.getIsDefault().equals(NOT_DEFAULT)) {
            address.setIsDefault(NOT_DEFAULT);
            queryWrapper.eq(Address::getAid, address.getAid());
            update(address, queryWrapper);
            return ResponseResult.okResult();
        }
        //先全部改为默认状态
        queryWrapper.eq(Address::getUid, SecurityUtils.getUserId());
        //用户的全部地址数据
        List<Address> addressList = list(queryWrapper);
        //过滤出默认地址,理论为一个
        List<Address> collect = addressList.stream().filter(a ->
                a.getIsDefault().equals(IS_DEFAULT)
        ).collect(Collectors.toList());
        collect.forEach(a -> {
            a.setIsDefault(NOT_DEFAULT);
            queryWrapper.eq(Address::getAid, a.getAid());
            update(a, queryWrapper);
        });
        //最后将选择修改的非默认地址改为默认
        LambdaQueryWrapper<Address> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Address::getAid, address.getAid());
        address.setIsDefault(IS_DEFAULT);
        update(address, queryWrapper2);

        return ResponseResult.okResult();
    }

    @Override
    public List<Address> getUserAddresslist() {
        //取出登录用户的id
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUid, userId);
        queryWrapper.orderByDesc(Address::getIsDefault);
        List<Address> addressList = list(queryWrapper);
        return addressList;
    }


}

