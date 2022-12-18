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
    public static final Integer TODAY_GOODS_TTL = 24*60;//24小时

//    public static final String LOGIN_CODE_KEY = "login:code:";
//    public static final Long LOGIN_CODE_TTL = 2L;//存在时间
//    public static final String LOGIN_USER_KEY = "login:token:";
//    public static final Long LOGIN_USER_TTL = 60*24*7L;
//
//    public static final Long CACHE_NULL_TTL = 2L;
//
//    public static final Long CACHE_SHOP_TTL = 30L;
//    public static final String CACHE_SHOP_KEY = "cache:shop:";
//    public static final String SHOP_LIST_KEY = "shop:list";
//    public static final Long SHOP_LIST_TTL = 30L;
//
//    public static final String LOCK_SHOP_KEY = "lock:shop:";
//    public static final Long LOCK_SHOP_TTL = 10L;
//
//    public static final String SECKILL_STOCK_KEY = "seckill:stock:";
//    public static final String BLOG_LIKED_KEY = "blog:liked:";
//    public static final String FEED_KEY = "feed:";
//    public static final String SHOP_GEO_KEY = "shop:geo:";
//    public static final String USER_SIGN_KEY = "sign:";
}
