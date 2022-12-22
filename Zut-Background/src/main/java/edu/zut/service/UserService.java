package edu.zut.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.entity.User;
import edu.zut.domain.vo.UserInfoVo;
import edu.zut.domain.vo.UserRoleVo;

/**
 * (User)表服务接口
 *
 * @author roydon
 * @since 2022-12-12 10:08:32
 */
public interface UserService extends IService<User> {

    UserInfoVo getUserInfo(Integer uid);
    boolean updateUserInfo(User user);
    Integer setDeletedByUid(Integer uid);

    Page<UserRoleVo> userRolePage(Integer pageNum, Integer pageSize, String username, String phone, String email);

    boolean saveOrUpdateUser(User user);
}

