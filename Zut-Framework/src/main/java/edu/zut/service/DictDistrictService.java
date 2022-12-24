package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.entity.DictDistrict;
import edu.zut.domain.vo.Province;

import java.util.List;


/**
 * (DictDistrict)表服务接口
 *
 * @author roydon
 * @since 2022-12-15 12:14:03
 */
public interface DictDistrictService extends IService<DictDistrict> {

    List<Province> allList();
}

