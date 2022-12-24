package edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Address;
import edu.zut.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author roydon
 **/
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 分页查询用户收货地址数据
     *
     * @param pageNum 页号
     * @param pageSize 大小
     * @return Page<Address>
     */
    @GetMapping("/page")
    public ResponseResult selectAllAddress(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        return ResponseResult.okResult(addressService.userAddressList(pageNum, pageSize));
    }

    /**
     * 新增收货地址
     *
     * @param address 地址
     * @return okResult
     */
    @PostMapping("/add")
    public ResponseResult addAddress(@RequestBody Address address) {
        return ResponseResult.okResult(addressService.addAddress(address));
    }

    /**
     * 删除地址数据
     *
     * @param aid 地址aid
     * @return okResult
     */
    @DeleteMapping("/{aid}")
    public ResponseResult delete(@PathVariable Integer aid) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getAid, aid);
        return ResponseResult.okResult(addressService.remove(queryWrapper));
    }

    /**
     * 批量删除收货地址
     *
     * @param aids [1，2，3，...]
     * @return okResult
     */
    @DeleteMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> aids) {
        return ResponseResult.okResult(addressService.removeByIds(aids));
    }


    /**
     * 更新地址
     *
     * @param address address
     * @return okResult
     */
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Address address) {
        return ResponseResult.okResult(addressService.updateAddress(address));
    }

    /**
     * 设为默认
     *
     * @param address address
     * @return okResult
     */
    @PostMapping("/setDefault")
    public ResponseResult setDefault(@RequestBody Address address) {
        return ResponseResult.okResult(addressService.setDefaultAddress(address));
    }

    /**
     * 非分页查询用户地址
     *
     * @return List<Address>
     */
    @GetMapping("/list")
    public ResponseResult getList() {
        return ResponseResult.okResult(addressService.getUserAddresslist());
    }

}
