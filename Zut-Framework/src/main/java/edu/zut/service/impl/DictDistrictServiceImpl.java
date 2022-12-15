package edu.zut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.TDictDistrict;
import edu.zut.mapper.DictDistrictMapper;
import edu.zut.service.DictDistrictService;
import org.springframework.stereotype.Service;

/**
 * (TDictDistrict)表服务实现类
 *
 * @author makejava
 * @since 2022-12-15 12:14:03
 */
@Service("tDictDistrictService")
public class DictDistrictServiceImpl extends ServiceImpl<DictDistrictMapper, TDictDistrict> implements DictDistrictService {

}

