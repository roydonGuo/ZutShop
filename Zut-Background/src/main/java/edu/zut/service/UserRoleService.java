package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.entity.UserRole;


/**
 * (UserRole)表服务接口
 *
 * @author roydon
 * @since 2022-12-23 21:41:41
 */
public interface UserRoleService extends IService<UserRole> {

    boolean addUserRole(UserRole userRole);
}

