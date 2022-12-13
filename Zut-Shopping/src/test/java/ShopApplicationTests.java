import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.zut.ShopApplication;
import edu.zut.domain.dto.UserDto;
import edu.zut.domain.entity.User;
import edu.zut.service.UserService;
import edu.zut.utils.BeanCopyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: roydon - 2022/12/12
 **/
@SpringBootTest(classes = ShopApplication.class)
public class ShopApplicationTests {
    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void pwdTest() {
        String pwd = passwordEncoder.encode("123456");
        System.out.println(pwd);
    }

    @Test
    void getUserListTest() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(User::getModifiedTime);
        List<User> userList = userService.list(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    void getOneUserTest() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, "roydon");
        queryWrapper.eq(User::getPassword, "$2a$10$h6G6o4KbDwrxHQYll/Ti1eZd3obUhQROlybMUnhWRJrbR8/UI/TP.");
        User user = userService.getOne(queryWrapper);
        System.out.println("user = " + user);
    }

    @Test
    void insertUserTest() {
        userService.save(BeanCopyUtils.copyBean(new UserDto("tom", passwordEncoder.encode("123456"),null), User.class));
    }

    @Test
    void updateTest(){
        //取出登录用户的id
        //查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, 1);
        queryWrapper.eq(User::getPassword, "123456");
        System.out.println("count+++++++++++++++++++++++++++"+userService.count(queryWrapper));

    }
}
