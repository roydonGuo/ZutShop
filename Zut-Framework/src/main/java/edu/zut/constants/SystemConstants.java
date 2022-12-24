package edu.zut.constants;

public class SystemConstants {

    /**
     * 正常状态
     */
    public static final String NORMAL = "0";

    /**
     * 用户是否为删除状态
     */
    public static final Integer IS_DELETED = 1;
    public static final Integer IS_ALIVE = 0;

    /**
     * 默认收货地址
     */
    public static final Integer IS_DEFAULT = 1;
    public static final Integer NOT_DEFAULT = 0;

    /**
     * goods商品的上架状态
     */
    public static final Integer ON_THE_SHELVES = 1;
    public static final Integer NOT_ON_SHELVES = 2;
    public static final Integer GOOD_IS_DELETED = 3;

    /**
     * 订单的状态：0-未支付，1-已支付，2-已取消，3-未发货
     */
    public static final Integer ORDER_CREATED = 0;
    public static final Integer ORDER_PAID = 1;
    public static final Integer ORDER_CANCEL = 2;
    public static final Integer ORDER_NOT_DELIVERED = 3;

    /**
     * 订单中的商品已签收
     */
    public static final Integer GOODS_SURE_RECEIVE = 4;

}