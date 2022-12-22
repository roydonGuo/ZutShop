package edu.zut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zut.domain.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Author: roydon - 2022/12/12
 **/
public interface UserMapper extends BaseMapper<User> {
    List<String> selectRoleByUid(Integer uid);

    @Update("update t_user set is_delete=1 where uid=#{uid}")
    Integer setDeletedByUid(Integer uid);

    @Insert("insert into t_user_role (uid,rid)values (#{uid},#{rid})")
    Integer insertUserRole(Integer uid, Integer rid);

    Integer insertUser(User user);
}
