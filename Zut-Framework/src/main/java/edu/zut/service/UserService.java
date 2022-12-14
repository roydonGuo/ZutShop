package edu.zut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.dto.UserDto;
import edu.zut.domain.entity.User;


/**
 * (User)表服务接口
 *
 * @author roydon
 * @since 2022-12-12 10:08:32
 */
public interface UserService extends IService<User> {

    ResponseResult register(UserDto userDto);
    ResponseResult updatePwd(UserDto userDto);

    ResponseResult getUserInfo(Integer uid);
    ResponseResult updateUserInfo(User user);
}

