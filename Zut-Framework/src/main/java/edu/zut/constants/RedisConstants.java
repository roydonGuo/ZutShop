package edu.zut.constants;

public class RedisConstants {

    public static final String LOGIN_USER_KEY = "login:user:";
    //后台登录角色redis前缀
    public static final String LOGIN_ADMIN_KEY = "login:admin:";

    //商品列表
    public static final String GOODS_LIST_KEY = "goods:list:";
    //商品列表存储过期时间
    public static final Integer GOODS_LIST_TTL = 60;//1小时

    //今日热销商品排行
    public static final String TODAY_GOODS_KEY = "goods:today:";
    //商品列表存储过期时间
    public static final Integer TODAY_GOODS_TTL = 24 * 60;//24小时

}
