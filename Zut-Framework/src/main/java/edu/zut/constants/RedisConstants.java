package edu.zut.constants;

/**
 * redis存储前缀
 */
public class RedisConstants {

    // 登录用户
    public static final String LOGIN_USER_KEY = "login:user:";
    //今日热销商品排行
    public static final String TODAY_GOODS_KEY = "goods:today:";
    //今日热销商品列表存储过期时间 24小时
    public static final Integer TODAY_GOODS_TTL = 24 * 60 * 60 * 1000;

}
