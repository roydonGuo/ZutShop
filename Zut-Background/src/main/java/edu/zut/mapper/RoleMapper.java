package edu.zut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zut.domain.entity.Role;

import java.util.List;


/**
 * (Role)表数据库访问层
 *
 * @author roydon
 * @since 2022-12-21 23:15:58
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getUserRoleList(Integer uid);
}

