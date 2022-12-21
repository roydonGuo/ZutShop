package edu.zut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.Role;
import edu.zut.mapper.RoleMapper;
import edu.zut.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author roydon
 * @since 2022-12-21 23:16:00
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

