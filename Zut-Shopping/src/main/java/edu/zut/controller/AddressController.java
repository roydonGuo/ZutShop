package edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Address;
import edu.zut.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: roydon - 2022/12/14
 **/
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 分页查询用户收货地址数据
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public ResponseResult selectAllAddress(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        return ResponseResult.okResult(addressService.userAddressList(pageNum, pageSize));
    }

    /**
     * 新增收货地址
     *
     * @param address
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addAddress(@RequestBody Address address) {
        return ResponseResult.okResult(addressService.addAddress(address));
    }

    /**
     * 删除地址数据
     *
     * @param aid
     * @return
     */
    @DeleteMapping("/{aid}")
    public ResponseResult delete(@PathVariable Integer aid) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getAid, aid);
        return ResponseResult.okResult(addressService.remove(queryWrapper));
    }

    /**
     * 更新地址
     * @param address
     * @return
     */
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Address address) {
        return ResponseResult.okResult(addressService.updateAddress(address));
    }

    /**
     * 设为默认
     * @param address
     * @return
     */
    @PostMapping("/setDefault")
    public ResponseResult setDefault(@RequestBody Address address){
        return ResponseResult.okResult(addressService.setDefaultAddress(address));
    }

}
