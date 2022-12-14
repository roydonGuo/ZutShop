package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Address;

import java.util.List;


/**
 * (Address)表服务接口
 *
 * @author roydon
 * @since 2022-12-14 22:08:25
 */
public interface AddressService extends IService<Address> {

    ResponseResult userAddressList(Integer pageNum, Integer pageSize);

    ResponseResult addAddress(Address address);

    ResponseResult updateAddress(Address address);

    ResponseResult setDefaultAddress(Address address);

    List<Address> getUserAddresslist();
}

