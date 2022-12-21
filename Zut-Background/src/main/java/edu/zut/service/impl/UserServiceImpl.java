package edu.zut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.User;
import edu.zut.mapper.UserMapper;
import edu.zut.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author roydon
 * @since 2022-12-12 10:08:33
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}

