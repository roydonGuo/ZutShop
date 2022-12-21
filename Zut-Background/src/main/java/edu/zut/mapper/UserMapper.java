package edu.zut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zut.domain.entity.User;

import java.util.List;

/**
 * Author: roydon - 2022/12/12
 **/
public interface UserMapper extends BaseMapper<User> {
    List<String> selectRoleByUid(Integer uid);
}
