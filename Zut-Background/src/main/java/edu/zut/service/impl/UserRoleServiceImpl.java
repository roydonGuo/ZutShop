package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.UserRole;
import edu.zut.mapper.UserRoleMapper;
import edu.zut.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author roydon
 * @since 2022-12-23 21:41:41
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public boolean addUserRole(UserRole userRole) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        //若数据库存在此uid和rid则不做处理
        queryWrapper.eq(UserRole::getUid, userRole.getUid());

        boolean saveOrUpdate = saveOrUpdate(userRole, queryWrapper);
        return saveOrUpdate;


    }
}

