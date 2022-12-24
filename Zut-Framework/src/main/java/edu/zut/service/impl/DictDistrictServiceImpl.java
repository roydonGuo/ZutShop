package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.DictDistrict;
import edu.zut.domain.vo.Area;
import edu.zut.domain.vo.City;
import edu.zut.domain.vo.Province;
import edu.zut.mapper.DictDistrictMapper;
import edu.zut.service.DictDistrictService;
import edu.zut.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (DictDistrict)表服务实现类
 *
 * @author roydon
 * @since 2022-12-15 12:14:03
 */
@Service("dictDistrictService")
public class DictDistrictServiceImpl extends ServiceImpl<DictDistrictMapper, DictDistrict> implements DictDistrictService {

    @Override
    public List<Province> allList() {

        LambdaQueryWrapper<DictDistrict> queryWrapper = new LambdaQueryWrapper<DictDistrict>();
        queryWrapper.eq(DictDistrict::getParent, 86);

        List<DictDistrict> dictDistrictList = list(queryWrapper);
        List<Province> provinceList = BeanCopyUtils.copyBeanList(dictDistrictList, Province.class);

        provinceList.forEach(p -> {

            String pCode = p.getCode();
            LambdaQueryWrapper<DictDistrict> queryWrapper2 = new LambdaQueryWrapper<DictDistrict>();
            queryWrapper2.eq(DictDistrict::getParent, pCode);
            List<DictDistrict> dictDistrictList2 = list(queryWrapper2);
            List<City> cityList = BeanCopyUtils.copyBeanList(dictDistrictList2, City.class);
            //区的封装
            cityList.forEach(c -> {
                String cCode = c.getCode();
                LambdaQueryWrapper<DictDistrict> queryWrapper3 = new LambdaQueryWrapper<DictDistrict>();
                queryWrapper3.eq(DictDistrict::getParent, cCode);
                List<DictDistrict> dictDistrictList3 = list(queryWrapper3);
                List<Area> areaList = BeanCopyUtils.copyBeanList(dictDistrictList3, Area.class);
                c.setAreaList(areaList);
            });

            p.setCityList(cityList);

        });

        return provinceList;
    }
}

