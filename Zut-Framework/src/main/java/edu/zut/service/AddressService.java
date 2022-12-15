package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Address;


/**
 * (TAddress)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 22:08:25
 */
public interface AddressService extends IService<Address> {

    ResponseResult userAddressList(Integer pageNum, Integer pageSize);

    ResponseResult addAddress(Address address);

    ResponseResult updateAddress(Address address);

    ResponseResult setDefaultAddress(Address address);
}

