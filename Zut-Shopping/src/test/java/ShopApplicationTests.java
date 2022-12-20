import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zut.ShopApplication;
import edu.zut.domain.entity.DictDistrict;
import edu.zut.domain.entity.Goods;
import edu.zut.domain.entity.Order;
import edu.zut.domain.entity.User;
import edu.zut.domain.vo.Area;
import edu.zut.domain.vo.CartGoodsVo;
import edu.zut.domain.vo.City;
import edu.zut.domain.vo.Province;
import edu.zut.mapper.OrderMapper;
import edu.zut.service.*;
import edu.zut.utils.BeanCopyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

import static edu.zut.constants.SystemConstants.ORDER_CREATED;

/**
 * Author: roydon - 2022/12/12
 **/
@SpringBootTest(classes = ShopApplication.class)
public class ShopApplicationTests {
    @Resource
    private UserService userService;
    @Resource
    private DictDistrictService dictDistrictService;
    @Resource
    private OrderService orderService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private CartService cartService;
    @Resource
    private OrderMapper orderMapper;
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
//        userService.save(BeanCopyUtils.copyBean(new UserDto("tom", passwordEncoder.encode("123456"),null), User.class));
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, 1);
        User user = userService.getOne(queryWrapper);
        System.out.println(user);

    }

    @Test
    void updateTest() {
        //取出登录用户的id
        //查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, 1);
        queryWrapper.eq(User::getPassword, "123456");
        System.out.println("count+++++++++++++++++++++++++++" + userService.count(queryWrapper));

    }

    @Test
    void getDistrict() {
        LambdaQueryWrapper<DictDistrict> queryWrapper = new LambdaQueryWrapper<DictDistrict>();
        queryWrapper.eq(DictDistrict::getParent, 86);

        List<DictDistrict> dictDistrictList = dictDistrictService.list(queryWrapper);
        List<Province> provinceList = BeanCopyUtils.copyBeanList(dictDistrictList, Province.class);

        provinceList.forEach(p -> {

            String pCode = p.getCode();
            LambdaQueryWrapper<DictDistrict> queryWrapper2 = new LambdaQueryWrapper<DictDistrict>();
            queryWrapper2.eq(DictDistrict::getParent, pCode);
            List<DictDistrict> dictDistrictList2 = dictDistrictService.list(queryWrapper2);
            List<City> cityList = BeanCopyUtils.copyBeanList(dictDistrictList2, City.class);
            //区的封装
            cityList.forEach(c -> {
                String cCode = c.getCode();
                LambdaQueryWrapper<DictDistrict> queryWrapper3 = new LambdaQueryWrapper<DictDistrict>();
                queryWrapper3.eq(DictDistrict::getParent, cCode);
                List<DictDistrict> dictDistrictList3 = dictDistrictService.list(queryWrapper3);
                List<Area> areaList = BeanCopyUtils.copyBeanList(dictDistrictList3, Area.class);
                c.setAreaList(areaList);
            });

            p.setCityList(cityList);

        });

        provinceList.forEach(System.out::println);
    }

    @Test
    void getOrderById() {
        Order order = orderService.getOrderByOid(822442);
        System.out.println(order);
    }

    @Test
    void searchGood() {
        // 查询20条数据
        Page<Goods> goodsPage = goodsService.searchGoodListByTitle(1, 20, "联想");
        System.out.println(goodsPage);
    }

    /**
     * 获取用户购物车数据VO
     */
    @Test
    void getUserCart() {
        List<CartGoodsVo> cartGoodsVoList = cartService.userCartGoodList(1);
        cartGoodsVoList.forEach(System.out::println);
    }

    @Test
    void createOrderTest() {



    }

}
