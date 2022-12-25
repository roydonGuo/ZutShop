# ZutShop

Junior practical training project

> 本人大三做的实训项目，前后端分离，包含后台管理。
>
> 后端地址：[https://github.com/roydonGuo/ZutShop](https://github.com/roydonGuo/ZutShop)
>
> 前端使用Vue，前端项目地址：[https://github.com/roydonGuo/ZutShop-Vue](https://github.com/roydonGuo/ZutShop-Vue)


---

![zhiziLogo](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212251930999.png)

# 0. 项目介绍

项目名称：智子商城。类似于购物车系统、订单系统。前后端分离。

![image-20221225195256378](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212251952985.png)

分析项目：

当开发某一个项目时，分许该项目中需要处理哪些数据？

例如：用户，收货地址，商品类别，商品，收藏，购物车，订单。。。。

处理数据的先后顺序：先处理基础数据，在处理相关数据，

例如需要先处理商品数据，才可以处理订单数据

处理以上数据的先后顺序：用户>收获地址>商品类别>商品>收藏>购物车>订单

当确定了数据处理顺序之后应该分析每个数据对应的功能有哪些

例如用户数据：注册，登录，修改密码，修改资料，上传头像。。。。

确定开发功能的开发顺序，遵循增>查>删>改

用户数据功能的开发顺序：注册>登录>修改密码>修改资料>上传头像。。。。

确定每一个功能的开发步骤：创建数据表>创建实体类>持久层>业务层>控制器层>前端页面

# 1. 数据库创建

关于项项目的数据表问题：

1. 根据虚拟的前端页面和业务需求创建出各种数据表
2. 画出各种数据表之间的关系图
3. 每个表中的每个字段都应该有其作用否则将直接去掉

针对本项目，数据库设计如下：

![image-20221222214609270](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212222146413.png)

sql文件在项目resources中。

# 2. 项目准备

本项目使用springboot框架进行后台开发，前端使用Vue开发，axios进行前后端交互。所以，涉及到的技术栈有：

* springboot
* springSecurity
* maven
* redis
* mybatis-plus
* Vue，axios

## 2.1 创建maven项目



父工程管理依赖：

```xml
<properties>
    <java.version>1.8</java.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
</properties>
<dependencyManagement>
    <dependencies>
        <!-- SpringBoot的依赖配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.5.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!--fastjson依赖-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.33</version>
        </dependency>
        <!--jwt依赖-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.0</version>
        </dependency>
        <!--mybatisPlus依赖-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

子工程`Zut-Framework`

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!--junit-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <!--redis依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!--SpringSecurity启动器-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
        <version>2.5.14</version>
    </dependency>
    <!--fastjson依赖-->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
    </dependency>
    <!--jwt依赖-->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
    </dependency>
    <!--mybatisPlus依赖-->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
    </dependency>
    <!--mysql数据库驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <!--AOP-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
    </dependency>
</dependencies>
```

子工程`Zut-Shopping`依赖于子工程`Zut-FrameWork`

```xml
<dependencies>
    <dependency>
        <groupId>edu.zut</groupId>
        <artifactId>Zut-Framework</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

## 2.2 Zut-Shopping

`Zut-Shopping`为业务控制层，需要一个启动类：

并添加mapper包扫描和开启swagger

```java
@SpringBootApplication
@MapperScan("edu.zut.mapper")
@EnableSwagger2
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
```

配置文件：

配置服务端口号7777，数据库信息，配置redis和MP。

```yml
server:
  port: 7777
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/zut-shop?characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: qwer1234
    driver-class-name: com.mysql.cj.jdbc.Driver
```

## 2.3 Zut-Framework

`Zut-Framework`负责业务接口的实现。

新建User实体类：

IDEA有插件`easycode`快速生成MP代码，或者使用官方提供的代码生成器。

```java
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
    //用户id@TableId
    private Integer uid;
    //用户名
    @ApiModelProperty(value = "用户名")
    private String username;
    //密码
    private String password;
    //性别,0-女,1-男
    private Integer gender;
    //电话
    private String phone;
    //邮箱
    private String email;
    //头像
    private String avatar;
    //是否删除,0-未删除,1-已删除
    private Integer isDelete;
    //创建执行人
    private Integer createdUser;
    //创建时间
    private Date createdTime;
    //修改执行人
    private Integer modifiedUser;
    //修改时间
    private Date modifiedTime;
}
```

UserMapper接口:

```java
/**
 * Author: roydon - 2022/12/12
 **/
public interface UserMapper extends BaseMapper<User> {}
```

Service层UserService：

```java
public interface UserService extends IService<User> {}
```

UserService的实现类UserServiceImpl

```java
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {}
```

新建数据相应类ResponseResult，表示后端响应给前端的数据。

```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseResult errorResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }

    public static ResponseResult okResult() {
        ResponseResult result = new ResponseResult();
        return result;
    }

    public static ResponseResult okResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, null, msg);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums) {
        return setAppHttpCodeEnum(enums, enums.getMsg());
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums, String msg) {
        return setAppHttpCodeEnum(enums, msg);
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getMsg());
    }

    private static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg) {
        return okResult(enums.getCode(), msg);
    }

    public ResponseResult<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
```

新建一个枚举类，封装响应码对应响应信息。

```java
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    USER_IS_DELETED(404, "用户已被删除"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONENUMBER_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    CONTENT_NOT_NULL(506, "评论内容不能为空"),
    FILE_TYPE_ERROR(507, "文件类型错误，请上传png文件"),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    NICKNAME_NOT_NULL(509, "昵称不能为空"),
    PASSWORD_NOT_NULL(510, "密码不能为空"),
    PASSWORD_NOT_MATCH(513, "原密码错误"),
    EMAIL_NOT_NULL(511, "邮箱不能为空"),
    NICKNAME_EXIST(512, "昵称已存在"),
    LOGIN_ERROR(505, "用户名或密码错误");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
```

跟redis的key相关字符串的封装：

```java
public class RedisConstants {
    public static final String LOGIN_USER_KEY="login:user:";
}
```

一些需要用到的常量的封装：

```java
public class SystemConstants {

    /**
     * 正常状态
     */
    public static final String NORMAL = "0";
    public static final String ADMAIN = "1";

    /**
     * 用户是否为删除状态
     */
    public static final Integer IS_DELETED = 1;
    public static final Integer IS_ALIVE = 0;

}
```

## 2.4 MP配置

配置MP分页器。这个功能也许用不到，针对数据多的时候分页查询会减小很多额外的性能消耗。

```java
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
```

分析数据表t_user可知，当我们在crud时，同时需要修改创建时间、修改时间、修改人、创建人等字段。

例如下面场景：修改用户密码后，实现类中还需要单独设置修改人、获取系统时间对修改时间字段进行设置。麻烦不说，写起来十分别扭不美观。所以，解决方法MP已经为我们想到了，只需要实现`MetaObjectHandler`接口即可。每当调用MP提供的相应方法，下面实现类对应的方法就会执行。

```java
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1;//表示是自己创建
        }
        this.setFieldValByName("createdTime", new Date(), metaObject);
        this.setFieldValByName("createdUser",userId , metaObject);
        this.setFieldValByName("modifiedTime", new Date(), metaObject);
        this.setFieldValByName("modifiedUser", userId, metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifiedTime", new Date(), metaObject);
        this.setFieldValByName("modifiedUser", SecurityUtils.getUserId(), metaObject);
    }
}
```

同时，User实体类要添加`@TableField`注解，添加规则

```java
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
    //用户id@TableId
    private Integer uid;
    //用户名
    @ApiModelProperty(value = "用户名")
    private String username;
    //密码
    private String password;
    //性别,0-女,1-男
    private Integer gender;
    //电话
    private String phone;
    //邮箱
    private String email;
    //头像
    private String avatar;
    //是否删除,0-未删除,1-已删除
    private Integer isDelete;
    //创建执行人
    @TableField(fill = FieldFill.INSERT)//插入时触发handler
    private Integer createdUser;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    //修改执行人
    @TableField(fill = FieldFill.INSERT_UPDATE)//更新时触发handler
    private Integer modifiedUser;
    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;
}
```

配置文件添加MP配置：

```yml
server:
  port: 7777

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/zut-shop?characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: qwer1234
    driver-class-name: com.mysql.cj.jdbc.Driver
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl #sql打印
  global-config:
    db-config:
      logic-delete-field: delFlag
      logic-delete-value: 1
      logic-not-delete-value: 0
      id-type: auto
```

> `注意：`MP配置好后最好在测试类中测试能否拿到数据库数据。

## 2.5 Redis配置

需要用到ali的fastjson序列化器在redis存值的时候进行序列化，保证是转义后的中文字符。

`FastJsonRedisSerializer`

```java
/**
 * Redis使用FastJson序列化
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private Class<T> clazz;

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz);
    }

    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
```

`RedisConfig`

```java
@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
```

redis操作封装工具类，来自ruoyi：

```java
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCache {

    @Resource
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 根据map的key对值进行自增操作
     *
     * @param key
     * @param hKey
     * @param v
     */
    public void incrementCacheMapValue(String key, String hKey, long v) {
        redisTemplate.boundHashOps(key).increment(hKey, v);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key
     * @param hkey
     */
    public void delCacheMapValue(final String key, final String hkey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hkey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
```

配置文件添加redis配置：我的redis没有设置密码，省略密码配置

```yml
server:
  port: 7777

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/zut-shop?characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: qwer1234
    driver-class-name: com.mysql.cj.jdbc.Driver
  redis:
    host: 127.0.0.1
    port: 6379
    database: 7
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: delFlag
      logic-delete-value: 1
      logic-not-delete-value: 0
      id-type: auto
```

## 2.6 Swagger配置

配Swagger置写在`shopping`模块

* RequestHandlerSelectors.basePackage("edu.zut.controller")中填写控制层的包
* ApiInfo可以根据自己需要自行配置

```java
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.zut.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("roydon", "https://www.roydon.top", "3133010060@qq.com");
        return new ApiInfoBuilder()
                .title("zut-shop")
                .description("智子商城")
                .contact(contact)   // 联系方式
                .version("1.0.0")  // 版本
                .build();
    }
}
```

启动类添加注解`@EnableSwagger2`启动Swagger

项目启动访问`http://localhost:7777/swagger-ui.html`即可

具体使用可参考官方网站，或前往：[http://c.biancheng.net/view/5532.html](http://c.biancheng.net/view/5532.html)

![image-20221213205613086](https://raw.githubusercontent.com/roydonGuo/Typora-Pic/main/md-pic202212211735392.png)

## 2.7 SpringSecurity配置

`Shopping`模块中新建类SecurityConfig

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问 anonymous
                .antMatchers("/user/login").anonymous()
                .anyRequest().authenticated();
    }

    /**
     * AuthenticationManager注册进容器
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
```

配置中注入好Security使用的加密工具BCryptPasswordEncoder后在测试类中测试密码加密功能是否正常。

此处引入spring容器管理的bean，重新new BCryptPasswordEncoder密码会匹配不上。

```java
@Resource
private PasswordEncoder passwordEncoder;

@Test
void pwdTest() {
    String pwd = passwordEncoder.encode("123456");
    System.out.println(pwd);
}
```

比较原始密码与加密密码，结果返回Boolean类型

```java
boolean matches = passwordEncoder.matches("123456",user.getPassword()); //true
```

# 3. 用户业务

## 3.1 登录登出接口

### 3.1.1登录

![image-20221225195346780](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212251953767.png)

controller

```java
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUsername())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

}
```

LoginService

```java
public interface LoginService {
    ResponseResult login(User user);
    ResponseResult logout();
}
```

LoginUser实体类，封装进User和用户的权限，权限本系统不需要，直接跳过返回null

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

实现`UserDetailsService`接口，重写`loadUserByUsername()`方法。暂且不需要角色权限。返回LoginUser对象

```java
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 方法引用
        queryWrapper.eq(StringUtils.isNotEmpty(username),User::getUsername,username);

        User user = userMapper.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //判断用户是否被删除
        if (Objects.equals(user.getIsDelete(), IS_DELETED)) {
            throw new SystemException(AppHttpCodeEnum.USER_IS_DELETED);
        }

        log.info("数据库登录用户：{}",user);
        //TODO 查询角色权限

        return new LoginUser(user);
    }

}
```

service实现类：

```java
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    /**
     * 登录
     * @param user
     * @return ResponseResult.okResult(userLoginVo)
     */
    @Override
    public ResponseResult login(User user) {
        //判断用户名是否为空
        if (StringUtils.isEmpty(user.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 认证成功，从Authentication获取LoginUser
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        log.info("loginUser:{}", loginUser);

        String userId = loginUser.getUser().getUid().toString();
        // 生成token
        String jwt = JwtUtil.createJWT(userId);
        // 存入redis
        redisCache.setCacheObject(LOGIN_USER_KEY + userId, loginUser);

        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        UserLoginVo userLoginVo = new UserLoginVo(jwt, userInfoVo);

        log.info("用户以登陆==>{}", userLoginVo);

        return ResponseResult.okResult(userLoginVo);
    }
}
```

封装的前后端交互实体`UserInfoVo`，把重要的信息隐藏起来。

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;

    private Integer gender;

    private String email;

}
```

相应给前端token实体类`UserLoginVo`

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private String token;
    private UserInfoVo userInfo;
}
```

自定义异常：前文枚举类已经定义过，就是此处构造方法参数AppHttpCodeEnum，当服务需要往外抛出错误时，指定响应码和对应响应信息，threw new SystemException()，把枚举AppHttpCodeEnum传递进去即可。

```java
public class SystemException extends RuntimeException {
    private int code;
    private String msg;

    public int getCode() {return code;}
    public String getMsg() {return msg;}

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
```

全局异常捕获handler：

`@RestControllerAdvice`这个注解继承了多个注解，包括Compont注解，作用之一就是可以自定义异常信息。当捕获到异常响应给且前端。

```java
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandle(SystemException se) {
        log.info("最喜欢异常了==>{}", se);
        return ResponseResult.errorResult(se.getCode(), se.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandle(Exception e) {
        log.info("最喜欢异常了==>{}", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
```

jwt认证过滤器：

```java
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //解析获取userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            // token超时，或token非法
            e.printStackTrace();
//            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//            WebUtils.renderString(response, JSON.toJSONString(responseResult));
            return;
        }
        String userId = claims.getSubject();
        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(LOGIN_USER_KEY + userId);
        //如果redis获取不到
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到 Authentication 中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

}
```

配置类添加jwt过滤器规则

```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// 关闭csrf
        http
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口、注册接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                //注销接口需要认证才能访问
                .antMatchers("/user/logout").authenticated()
                // 除上面外的所有请求全部不需要认证即可访问
                .anyRequest().permitAll();

        // jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //关闭默认的注销功能
        http.logout().disable();
        //允许跨域
        http.cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
```

Apifox测试登录接口：

![image-20221213214122124](https://raw.githubusercontent.com/roydonGuo/Typora-Pic/main/md-pic202212211736425.png)

redis也成功存入数据

![image-20221213214223209](https://raw.githubusercontent.com/roydonGuo/Typora-Pic/main/md-pic202212211736944.png)

### 3.1.2 登出

即用户登陆后选择退出登录。鼠标移动到头像弹出。

![image-20221225195412568](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212251954606.png)

controller接口

```java
@RequestMapping("/logout")
@ApiOperation(value = "退出登录")
public ResponseResult logout(){
    return loginService.logout();
}
```

实现类重写登出方法

```java
/**
 * 退出登录
 * 1.获取用户信息 SecurityContextHolder.getContext().getAuthentication();
 * 2.通过用户 id 清除 redis
 *
 * @return ResponseResult(CODE_200, " 退出成功 ");
 */
@Override
public ResponseResult logout() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    LoginUser loginUser = (LoginUser) authentication.getPrincipal();
    Integer uid = loginUser.getUser().getUid();
    redisCache.deleteObject(LOGIN_USER_KEY + uid);
    return new ResponseResult(CODE_200, "退出成功");
}
```

## 3.2 注册用户

前端页面：

![image-20221225195518826](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212251955906.png)

### 3.2.1 需求分析

前端发送ajax请求，确认密码由前端进行判断，前端只传给后端用户名、密码两个字段的json串。

* 前端传入数据格式：(json){ 'username': username, 'password': password }

* 请求地址：`/uesr/register`

### 3.2.2 后端实现

①. UserController写一个注册接口

```java
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResponseResult register(@RequestBody UserDto userDto) {
        return ResponseResult.okResult(userService.register(userDto));
    }

}
```

②. 前端传入对象封装

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "前端dto")
public class UserDto {
    //用户名
    private String username;
    //原密码
    private String password;
    //新密码
    private String newPassword;
}
```

③. 实现类重写接口中注册方法

```java
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult register(UserDto userDto) {
        //非空判断
        if (StringUtils.isEmpty(userDto.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (StringUtils.isEmpty(userDto.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        //数据库中是否存在此用户名
        if (userNameExist(userDto.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        //密码加密
        String encodePassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePassword);
        save(BeanCopyUtils.copyBean(userDto,User.class));
        return ResponseResult.okResult();
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userName);
        return count(queryWrapper) > 0;
    }

}
```

④. 对象拷贝工具封装

因为前端传过来的是UserDto，但执行sql需要用到数据表对应实体：User，所以封装一个对象拷贝工具转一下对象后再进行crud操作。

```java
/**
 * 对象、集合拷贝工具类
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    public static <V> V copyBean(Object source, Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <O, V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());
    }
}
```

## 3.3 修改密码

前端页面：

![image-20221225195703165](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212251957548.png)

### 3.3.1 需求分析

前端提供表单，表单内容包括用户名，原密码和新密码。在前端还会进行确认新密码的校验工作。当然，校验只放在前端即可。

![image-20221221173833197](https://raw.githubusercontent.com/roydonGuo/Typora-Pic/main/md-pic202212211738518.png)

* 前端传入数据格式：

> {
>
> ​	"username": "roydon",
>
> ​	"password": "123456",
>
> ​	"confirmPassword": "111111"
>
> }

发现前端传入数据与后端的`User`实体类字段数量相差甚远，甚至还有后端没有的字段。这就需要后端设计一个新的用于前后端进行数据传输的对象（DTO）命名为`UserDto`。

* 请求地址：`post("/user/password")`



### 3.3.2 后端实现

①新建`UserDto`实体类用来接收前端传入的数据，他屏蔽了大量的用户隐私信息。

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "注册用户dto")
public class UserDto {

    //用户名
    private String username;
    //原密码
    private String password;
    //新密码
    private String newPassword;

}
```

②控制层`UserController`编写一个接口

```java
/**
 * 修改密码
 *
 * @param userDto
 * @return
 */
@PostMapping("/password")
public ResponseResult update(@RequestBody UserDto userDto) {
    return ResponseResult.okResult(userService.updatePwd(userDto));
}
```

③业务层实现修改密码方法

```java
ResponseResult updatePwd(UserDto userDto);
```

```java
@Override
public ResponseResult updatePwd(UserDto userDto) {
    //非空判断
    if (StringUtils.isEmpty(userDto.getPassword())) {
        throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
    }
    //取出登录用户的id
    Integer userId =null;
    try {
        userId = SecurityUtils.getUserId();
    }catch (Exception e) {
        //未登录
        throw new SystemException(NEED_LOGIN);
    }
    if(Objects.isNull(userId)){
        //没有携带token
        throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
    }
    //查询用户
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getUid, userId);
    User one = getOne(queryWrapper);
    //判断输入密码是否与数据库相同
    boolean matches = passwordEncoder.matches(userDto.getPassword(),one.getPassword());
    if (!matches) {
        //不存在用户
        throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_MATCH);
    }
    //从redis中获取用户信息
    LoginUser loginUser = redisCache.getCacheObject(LOGIN_USER_KEY + userId);
    //如果redis获取不到
    if (Objects.isNull(loginUser)) {
        throw new RuntimeException("用户未登录");
    }
    //新密码加密
    String encodePassword = passwordEncoder.encode(userDto.getNewPassword());
    User user = loginUser.getUser();
    user.setPassword(encodePassword);
    log.info("修改后的用户：{}",user);
    redisCache.setCacheObject(LOGIN_USER_KEY + userId,new LoginUser(user) );
    return ResponseResult.okResult(update(user,queryWrapper));
}
```

## 3.4 更新用户信息

### 3.4.1 需求分析

![image-20221225195817867](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212251958240.png)

提供一个表单，即是展示用户信息的页面，也可直接进行修改，以及头像的上传。

* 前端接口：`post("/user/update")`

### 3.4.2 后端实现

①控制层

```java
/**
 * 更新用户信息
 *
 * @param user
 * @return
 */
@PostMapping("/update")
public ResponseResult updateUser(@RequestBody User user) {
    return ResponseResult.okResult(userService.updateUserInfo(user));
}
```

②业务层

```java
ResponseResult updateUserInfo(User user);
```

```java
@Override
public ResponseResult updateUserInfo(User user) {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getUid,user.getUid());
    redisCache.setCacheObject(LOGIN_USER_KEY + user.getUid(), new LoginUser(user));
    return ResponseResult.okResult(update(user,queryWrapper));
}
```

## 3.5 头像上传

### 3.5.1 需求分析

紧接着上面修改用户信息步骤，此次需求为修改用户头像。

* 前端接口：`http://localhost:7777/file/upload`

### 3.5.2 后端实现

①头像存储使用七牛云的对象存储服务，前往官网[https://portal.qiniu.com/kodo/bucket](https://portal.qiniu.com/kodo/bucket)。

注册完用户并登录，打开控制台选择存储空间：

![image-20221221180315679](https://raw.githubusercontent.com/roydonGuo/Typora-Pic/main/md-pic202212211803427.png)

选择新建空间，访问权限最好设置为公开，名称随意：

![image-20221221180406779](https://raw.githubusercontent.com/roydonGuo/Typora-Pic/main/md-pic202212211804134.png)

创建好存储空间，七牛云会给此空间提供一个为期三十天的测试域名，也就是说，你上传了图片，可以根据·此测试域名和图片名称访问此网络图片。

点开右上角个人头像创建密钥。

![image-20221221180918673](https://raw.githubusercontent.com/roydonGuo/Typora-Pic/main/md-pic202212211809654.png)

②项目引入依赖，在Framework工程引入依赖

```xml
<!--七牛云存储-->
<dependency>
    <groupId>com.qiniu</groupId>
    <artifactId>qiniu-java-sdk</artifactId>
    <version>[7.7.0, 7.7.99]</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.9.0</version>
</dependency>
```

添加配置

```yml
oss: #七牛云对象存储
  accessKey: 5QiJ****************WX-_O3i
  secretKey: S6t****************FTkFWx51
  bucket: zut-shop-avatar # 空间名称
```

③控制层`FileController`

```java
@PostMapping("/upload")
public ResponseResult uploadFile(@RequestParam MultipartFile file) {
    //头像上传
    return ResponseResult.okResult(fileService.uploadImg(file));
}
```

④业务层

```java
ResponseResult uploadImg(MultipartFile file);
```

```java
@Data
@Slf4j
@Service
//@ConfigurationProperties(prefix = "oss")
public class FileServiceImpl implements FileService {

    @Override
    public ResponseResult uploadImg(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        //对原始文件名进行判断
//        if(!originalFilename.endsWith(".png")){
//            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
//        }
        //上传文件到OSS
        assert originalFilename != null;
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOss(file, filePath);

        log.info("图片上传地址：{}", url);

        return ResponseResult.okResult(url);
    }

    @Value("${oss.accessKey}")
    private String accessKey;

    @Value("${oss.secretKey}")
    private String secretKey;

    @Value("${oss.bucket}")
    private String bucket;

    private String uploadOss(MultipartFile imgFile, String filePath) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://rm*******d-bkt.clouddn.com/" + key;//换成你自己的测试域名
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";
    }

}
```

# 4. 收货地址管理

## 4.1 增加收货地址

### 4.1.1 需求分析

![image-20221225195948753](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212251959652.png)

* 前端传入：`Address`实体

* 请求方式：`post("/address/add")`

### 4.1.2 后端实现

①控制层

```java
/**
 * 新增收货地址
 *
 * @param address
 * @return
 */
@PostMapping("/add")
public ResponseResult addAddress(@RequestBody Address address) {
    return ResponseResult.okResult(addressService.addAddress(address));
}
```

②业务层

```java
 @Override
public ResponseResult addAddress(Address address) {
    //取出登录用户的id
    Integer userId =  SecurityUtils.getUserId();
    LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Address::getUid, userId);
    //如果新增为第一条地址数据，则设为默认
    if (count(queryWrapper) < 1) {
        address.setIsDefault(IS_DEFAULT);
    }
    address.setUid(SecurityUtils.getUserId());
    save(address);
    return ResponseResult.okResult();
}
```

## 4.2 查询收货地址

### 4.2.1 需求分析

* 前端传入分页参数：@RequestParam Integer pageNum, @RequestParam Integer pageSize
* 请求地址：`get("/address/page")`

### 4.2.2 后端实现

① 控制层

```java
/**
* 分页查询用户收货地址数据
*
* @param pageNum
* @param pageSize
* @return
*/
GetMapping("/page")
ublic ResponseResult selectAllAddress(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
   return ResponseResult.okResult(addressService.userAddressList(pageNum, pageSize));
}
```

② 业务层，前期已经配置好MP分页插件。

```java
@Override
public ResponseResult userAddressList(Integer pageNum, Integer pageSize) {
    //取出登录用户的id
    Integer userId = SecurityUtils.getUserId();
    if (Objects.isNull(userId)) {
        //没有携带token
        throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
    }
    LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Address::getUid, userId);

    Page<Address> page = page(new Page<>(pageNum, pageSize), queryWrapper);

    return ResponseResult.okResult(page);
}
```

## 4.3 编辑收货地址

### 4.3.1 需求分析

![image-20221225200029514](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252000973.png)

* 前端传入`Address`实体
* 请求地址：`post("/address/update")`

### 4.3.2 后端实现

①控制层

```java
/**
  * 更新地址
  *
  * @param address
  * @return
  */
 @PostMapping("/update")
 public ResponseResult update(@RequestBody Address address) {
     return ResponseResult.okResult(addressService.updateAddress(address));
 }
```

②业务层

```java
@Override
public ResponseResult updateAddress(Address address) {
    LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Address::getAid, address.getAid());
    update(address, queryWrapper);
    return ResponseResult.okResult();
}
```

## 4.4 删除收货地址

### 4.4.1 需求分析

前端传入地址id，后端根据地址id删除

* 请求地址：`delete("/address/{aid}")`

### 4.4.2 后端实现

①控制层直接调用构造器删除

```java
 /**
 * 删除地址数据
 *
 * @param aid
 * @return
 */
@DeleteMapping("/{aid}")
public ResponseResult delete(@PathVariable Integer aid) {
    LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Address::getAid, aid);
    return ResponseResult.okResult(addressService.remove(queryWrapper));
}
```

## 4.5 收货地址设为默认

### 4.5.1 需求分析

当购买商品准备结算时，会有选择收货地址选项，设置默认收货地址下次购物直接忽略选择地址这一选项方便用户操作，优化用户体验。

* 前端传入数据格式为`Address实体类`
* 请求接口：`post("/address/setDefault")`

### 4.5.2 后端实现

①控制层

```java
/**
 * 设为默认
 *
 * @param address
 * @return
 */
@PostMapping("/setDefault")
public ResponseResult setDefault(@RequestBody Address address) {
    return ResponseResult.okResult(addressService.setDefaultAddress(address));
}
```

②业务层

此处需要多次操作数据库，加个`@Transactional`注解，自动回滚事务，保证数据库执行此方法的前后一致性。

```java
@Override
@Transactional
public ResponseResult setDefaultAddress(Address address) {
    LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
    //地址默认改为非默认
    if (address.getIsDefault().equals(NOT_DEFAULT)) {
        address.setIsDefault(NOT_DEFAULT);
        queryWrapper.eq(Address::getAid, address.getAid());
        update(address, queryWrapper);
        return ResponseResult.okResult();
    }
    //先全部改为默认状态
    queryWrapper.eq(Address::getUid, SecurityUtils.getUserId());
    //用户的全部地址数据
    List<Address> addressList = list(queryWrapper);
    //过滤出默认地址,理论为一个
    List<Address> collect = addressList.stream().filter(a ->
            a.getIsDefault().equals(IS_DEFAULT)
    ).collect(Collectors.toList());
    collect.forEach(a -> {
        a.setIsDefault(NOT_DEFAULT);
        queryWrapper.eq(Address::getAid, a.getAid());
        update(a, queryWrapper);
    });
    //最后将选择修改的非默认地址改为默认
    LambdaQueryWrapper<Address> queryWrapper2 = new LambdaQueryWrapper<>();
    queryWrapper2.eq(Address::getAid, address.getAid());
    address.setIsDefault(IS_DEFAULT);
    update(address, queryWrapper2);

    return ResponseResult.okResult();
}
```

# 5. 首页完善

## 5.1 添加今日热销栏

### 5.1.1 需求分析

![image-20221225200120607](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252001034.png)

* 前端负责渲染，后端只需根据商品表的修改时间字段进行查找，并制定查找条数。

### 5.1.2 后端实现

①控制层

```java
/**
 * 今日热销
 *
 * @param pageNum
 * @param pageSize
 * @return
 */
@GetMapping("/today")
public ResponseResult todayGood(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
    return ResponseResult.okResult(goodsService.todayGoodList(pageNum, pageSize));
}
```

②业务层

```java
@Override
public Page<Goods> todayGoodList(Integer pageNum, Integer pageSize) {
    //查询redis
    Page<Goods> todayGoodsList = redisCache.getCacheObject(TODAY_GOODS_KEY + pageNum);
    if (todayGoodsList == null) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        //按创建时间倒序排序
        queryWrapper.orderByDesc(Goods::getCreatedTime);
        Page<Goods> goodsPage = page(new Page<>(pageNum, pageSize), queryWrapper);
        //TODO 存入redis
        redisCache.setCacheObject(TODAY_GOODS_KEY + pageNum, goodsPage, TODAY_GOODS_TTL, TimeUnit.MINUTES);
        return goodsPage;
    } else {
        return todayGoodsList;
    }
}
```

## 5.2 商品展示页

### 5.2.1 需求分析

![image-20221225200142140](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252001722.png)

分页查询出商品及和，前端负责渲染

### 5.2.2 后端实现

①控制层

```java
/**
 * 分页查询所有商品【暂行方案】
 *
 * @param pageNum
 * @param pageSize
 * @return
 */
@GetMapping("/list")
public ResponseResult selectAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
    return ResponseResult.okResult(goodsService.goodList(pageNum, pageSize));
}
```

②业务层

```java
@Override
public ResponseResult goodList(Integer pageNum, Integer pageSize) {
    LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
    //按优先级排序
    queryWrapper.orderByDesc(Goods::getPriority);
    Page<Goods> page = page(new Page<>(pageNum, pageSize));
    return ResponseResult.okResult(page);
}
```

## 5.3 商品搜索

### 5.3.1 需求分析

![image-20221225200217850](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252002286.png)

前端在输入框输入商品名称，后端根据输入名称进行模糊查询并返回商品集合。例如上图输入“华硕”搜索结果。

### 5.3.2 后端实现

①控制层

```java
 /**
 * 根据商品名模糊搜索,并分页
 *
 * @param title 商品名
 * @return ResponseResult
 */
@GetMapping("/search")
public ResponseResult searchGood(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam String title) {
    return ResponseResult.okResult(goodsService.searchGoodListByTitle(pageNum, pageSize, title));
}
```

②业务层

```java
@Override
public Page<Goods> searchGoodListByTitle(Integer pageNum, Integer pageSize, String title) {
    LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.like(Goods::getTitle, title);
    //按优先级排序
    queryWrapper.orderByDesc(Goods::getPriority);
    Page<Goods> goodsPage = page(new Page<>(pageNum, pageSize), queryWrapper);
    return goodsPage;
}
```

## 5.4 商品收藏

### 5.4.1 需求分析

![image-20221225200310363](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252003694.png)

点击商品页加入收藏按钮添加到我的收藏中。

### 5.4.2 后端实现

①控制层

```java
/**
 * 添加商品收藏
 *
 * @param gid
 * @return
 */
@PostMapping("/add")
public ResponseResult add(@RequestBody Integer gid) {
    return ResponseResult.okResult(favoritesService.addFavorites(gid));
}
```

②业务层

```java
@Override
public boolean addFavorites(Integer gid) {
    //取出登录用户的id
    Integer userId = SecurityUtils.getUserId();
    Favorites favorites = new Favorites();
    favorites.setGid(gid);
    favorites.setUid(userId);
    boolean saveOrUpdate = saveOrUpdate(favorites);
    return saveOrUpdate;
}
```



# 6. 订单管理

## 6.1 查询订单列表

### 6.1.1 需求分析

![image-20221225200336242](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252003609.png)

查询出用户的所有订单，并根据订单的oid关联查询订单包含的商品集合。

此时需要一个实体类封装传输数据既包含订单信息，也包含订单商品集合信息。

### 6.1.2 后端实现

①新建`OrderGoodVo`实体类

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoodVo {

    private Integer oid;
    //归属于那个用户
    private Integer uid;
    //收货人
    private String name;
    //下单时间
    private Date orderTime;
    //是否发货
    private Integer status;
    //集合
    private List<OrderItem> orderItemList;
}
```

②控制层

```java
/**
 * 分页查询当前登录用户的所有订单
 *
 * @param pageNum pageNum
 * @param pageSize pageSize
 * @return ResponseResult
 */
@GetMapping("/list")
public ResponseResult selectAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
    return ResponseResult.okResult(orderService.userOrderList(pageNum, pageSize));
}
```

③业务层

前端需要分页展示，所以还得封装`Page<OrderGoodVo>`分页对象

```java
@Override
public ResponseResult userOrderList(Integer pageNum, Integer pageSize) {

    //取出登录用户的id
    Integer userId = null;
    try {
        userId = SecurityUtils.getUserId();
    } catch (Exception e) {
        //未登录
        throw new SystemException(NEED_LOGIN);
    }
    if (Objects.isNull(userId)) {
        //没有携带token
        throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
    }
    LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Order::getUid, userId);
    Page<Order> pageOrder = new Page<>(pageNum, pageSize);

    Page<Order> page = page(pageOrder, queryWrapper);

    List<Order> orderList = page.getRecords();
    //TODO 将订单包含的商品order_item封装进order
    List<OrderGoodVo> orderGoodVoList = new ArrayList<>();
    orderList.forEach(o -> {
        //订单od
        Integer oid = o.getOid();
        //根据订单id查询order_item集合
        LambdaQueryWrapper<OrderItem> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(OrderItem::getOid, oid);
        List<OrderItem> orderItemList = orderItemService.list(queryWrapper2);
        OrderGoodVo orderGoodVo = BeanCopyUtils.copyBean(o, OrderGoodVo.class);
        orderGoodVo.setOrderItemList(orderItemList);
        orderGoodVoList.add(orderGoodVo);
    });

    Page<OrderGoodVo> orderGoodVoPage = new Page<>();
    orderGoodVoPage.setCurrent(page.getCurrent());
    orderGoodVoPage.setPages(page.getPages());
    orderGoodVoPage.setSize(page.getSize());
    orderGoodVoPage.setTotal(page.getTotal());
    orderGoodVoPage.setRecords(orderGoodVoList);

    return ResponseResult.okResult(orderGoodVoPage);
}
```

## 6.2 查询订单商品详情

### 6.2.1 需求分析

![image-20221225200419387](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252004763.png)

点击订单商品的查看详情按钮，跳转到订单详情页面，前端根据订单商品的gid查询并显示。

### 6.2.2 后端实现

①业务层

在商品控制层编写

```java
 /**
 * 根据gid查询商品
 *
 * @param gid
 * @return
 */
@GetMapping("{gid}")
public ResponseResult getGoods(@PathVariable Integer gid) {
    LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Goods::getGid, gid);
    return ResponseResult.okResult(goodsService.getOne(queryWrapper));
}
```

## 6.3 确认收货

### 6.3.1 需求分析

当订单处于已支付状态，订单每个商品均显示确认收货按钮，用户点击后先删除订单关联的此商品，若订单没有再关联的商品则把订单删除或者设置状态为已签收。

### 6.3.2 后端实现

①控制层

```java
 /**
 * 订单中的商品已签收
 *
 * @param orderItem
 * @return
 */
@PostMapping("/receive")
public ResponseResult remove(@RequestBody OrderItem orderItem) {
    return ResponseResult.okResult(orderItemService.delOrderItem(orderItem));
}
```

②业务层

```java
@Override
@Transactional
public ResponseResult delOrderItem(OrderItem orderItem) {
    log.info("订单商品==>{}",orderItem);
    LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
    orderItemLambdaQueryWrapper.eq(OrderItem::getGid, orderItem.getGid());
    orderItemLambdaQueryWrapper.eq(OrderItem::getOid, orderItem.getOid());
    orderItemLambdaQueryWrapper.eq(OrderItem::getCreatedUser, SecurityUtils.getUserId());
    boolean remove = remove(orderItemLambdaQueryWrapper);
    //删除了订单中的商品，判断订单是否还有商品，没有则删除
    if (remove) {
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItem::getOid, orderItem.getOid());
        wrapper.eq(OrderItem::getCreatedUser, SecurityUtils.getUserId());
        List<OrderItem> orderList = list(wrapper);
        //此订单已经没有商品
        if (orderList.size() == 0) {
            //删除订单
            LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderLambdaQueryWrapper.eq(Order::getOid, orderItem.getOid());
            orderLambdaQueryWrapper.eq(Order::getUid, SecurityUtils.getUserId());
            boolean remove2 = orderService.remove(orderLambdaQueryWrapper);
        }
    }
    return ResponseResult.okResult();
}
```

# 7. 购物车管理

## 7.1 添加购物车

### 7.1.1 需求分析

![image-20221225200644888](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252006374.png)

点击商品会跳转到商品详情页，当用户需要选择加入购物车时，同样可以选择加入购物车的数量。

如果购物车已经有了该商品，则在此数据的基础之上进行更新操作。

* 前端传入数据格式：`{gid: 100000391, num: 3}`
* 请求接口：`post("/cart/add")`

### 7.1.2 后端实现

①控制层

```java
/**
 * 添加购物车
 *
 * @param cart
 * @return
 */
@PostMapping("/add")
public ResponseResult addCart(@RequestBody Cart cart) {
    return ResponseResult.okResult(cartService.addCartByUid(cart));
}
```

②业务层

```java
@Override
public ResponseResult addCartByUid(Cart cart) {
    //取出登录用户的id
    Integer userId = SecurityUtils.getUserId();
    cart.setUid(userId);
    if(cart.getNum()==0||cart.getNum()==null){
        cart.setNum(1);
    }
    //如果购物车有了此商品，就更新
    LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Cart::getUid, userId);
    queryWrapper.eq(Cart::getGid, cart.getGid());
    log.info("待加入购物车数据==>{}", cart);
    saveOrUpdate(cart,queryWrapper);
    return ResponseResult.okResult();
}
```

## 7.2 查询购物车

### 7.2.1 需求分析

![image-20221225200719190](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252007541.png)

跟据当前登录用户查询出其购物车数据即可。

* 请求接口：`get("/cart/list")`，参数为用户`uid`

### 7.2.2 后端实现

①控制层

```java
/**
 * 用户购物车数据
 *
 * @param uid
 * @return
 */
@RequestMapping("/list")
public ResponseResult userCart(@RequestParam Integer uid) {
    return ResponseResult.okResult(cartService.userCartGoodList(uid));
}
```

②业务层

```java
@Override
public List<CartGoodsVo> userCartGoodList(Integer uid) {
    //先根据用户id查询购物车数据
    LambdaQueryWrapper<Cart> cartLambdaQueryWrapper = new LambdaQueryWrapper<>();
    cartLambdaQueryWrapper.eq(Cart::getUid, uid);
    cartLambdaQueryWrapper.orderByDesc(Cart::getCreatedTime);
    List<Cart> cartList = list(cartLambdaQueryWrapper);
    //封装VO
    List<CartGoodsVo> cartGoodsVoList = new ArrayList<>();
    cartList.forEach(c -> {
        CartGoodsVo cartGoodsVo = BeanCopyUtils.copyBean(c, CartGoodsVo.class);
        //商品id
        Integer gid = c.getGid();
        LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsLambdaQueryWrapper.eq(Goods::getGid, gid);
        // TODO 待添加此商品是否被下架逻辑
        Goods goods = goodsService.getOne(goodsLambdaQueryWrapper);
        cartGoodsVo.setGoods(goods);
        cartGoodsVoList.add(cartGoodsVo);
    });
    return cartGoodsVoList;
}
```

## 7.3 删除购物车商品

### 7.3.1 需求分析

![gif-110](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252009639.gif)

前端点击删除按钮，购物车数据就会删除。

* 请求接口：`delete("/cart/")`，请求参数为购物车`cid`

### 7.3.2 后端实现

①控制层

```java
/**
 * 根据购物车id删除购物车
 *
 * @param cid
 * @return
 */
@DeleteMapping("/{cid}")
public ResponseResult deleteCart(@PathVariable Integer cid) {
    return ResponseResult.okResult(cartService.removeCartGoodByCid(cid));
}
```

②业务层

```java
@Override
public boolean removeCartGoodByCid(Integer cid) {
    LambdaQueryWrapper<Cart> cartLambdaQueryWrapper = new LambdaQueryWrapper<>();
    cartLambdaQueryWrapper.eq(Cart::getCid, cid);
    boolean remove = remove(cartLambdaQueryWrapper);
    return remove;
}
```



# 8. 整合支付宝支付(沙箱)

只是使用支付宝支付api进行交易模拟，需前往支付宝开发平台进行沙箱应用创建。

支付宝开发平台地址地址==>[https://open.alipay.com/develop/sandbox/app](https://open.alipay.com/develop/sandbox/app)

使用到的依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!--lombok-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<!--mysql数据库驱动-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<!--alipay-->
<dependency>
    <groupId>com.alipay.sdk</groupId>
    <artifactId>alipay-easysdk</artifactId>
    <version>2.2.0</version>
</dependency>
<!--mybatisPlus依赖-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.2</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.10</version>
</dependency>
```

需要的实体：`Alipay`

```java
@Data
public class AliPay {
    //订单号
    private String traceNo;
    //金额
    private String totalAmount;
    private String subject;
//    private String alipayTraceNo;
}
```

配置文件yml配置：

```yml
alipay:
  appId: 202100*******
  appPrivateKey: **************
  alipayPublicKey: *********
  notifyUrl: 
```

查看沙箱应用id、公钥和私钥补全配置。

alipay配置类用来加载配置信息：

```java
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {

    private static final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private static final String FORMAT ="JSON";
    private static final String CHARSET ="utf-8";
    private static final String SIGN_TYPE ="RSA2";

    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;

    @PostConstruct
    public void init() {
        // 设置参数（全局只需设置一次）
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = SIGN_TYPE;
        config.appId = this.appId;
        config.merchantPrivateKey = this.appPrivateKey;
        config.alipayPublicKey = this.alipayPublicKey;
        config.notifyUrl = this.notifyUrl;
        Factory.setOptions(config);
        System.out.println(JSONUtil.toJsonStr(config));
        log.info("=======支付宝SDK初始化成功=======");
    }
}
```

控制层写一个发起支付接口：

```java
/**
 * http://localhost:7778/alipay/pay?subject=15689585674&traceNo=1024253&totalAmount=3333
 *
 * @param aliPay
 * @return
 */
@GetMapping("/pay")
public String pay(AliPay aliPay) {
    AlipayTradePagePayResponse response;
    try {
        //  发起API调用（以创建当面付收款二维码为例）
        response = Factory.Payment.Page()
                .pay(URLEncoder.encode(aliPay.getSubject(), "UTF-8"), aliPay.getTraceNo(), aliPay.getTotalAmount(), "");
    } catch (Exception e) {
        System.err.println("调用遭遇异常，原因：" + e.getMessage());
        throw new RuntimeException(e.getMessage(), e);
    }
    return response.getBody();
}
```

打开谷歌浏览器无痕窗口访问写好的支付地址自动跳转到支付页面：

![image-20221222202104500](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212222021178.png)

此时就可以模拟支付款了，账号密码在支付宝开发平台皆可查到。

那支付成功后，就必然有一个回调，但支付宝平台是公网，我们本地项目它回调不过来，所以暂且需要把本地端口暴露到公网，使用内网穿透工具==>[https://natapp.cn/](https://natapp.cn/)

注册登录可申请免费内网穿透，指定端口号后拿到官网给的密钥，下载内网穿透工具配置好密钥即可把端口暴露到公网上。这个公网就是填写配置类yml中notifyUrl的选项。

支付宝回调接口：

```java
@Resource
private OrderMapper orderMapper;

@PostMapping("/notify")  // 必须是POST接口
public String payNotify(HttpServletRequest request) throws Exception {
    if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
        System.out.println("=========支付宝异步回调========");

        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            params.put(name, request.getParameter(name));
        }

        String tradeNo = params.get("out_trade_no");
        String payTime = params.get("gmt_payment");
        String alipayTradeNo = params.get("trade_no");
        // 支付宝验签
        if (Factory.Payment.Common().verifyNotify(params)) {
            // 验签通过
            System.out.println("交易名称: " + params.get("subject"));
            System.out.println("交易状态: " + params.get("trade_status"));
            System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
            System.out.println("商户订单号: " + params.get("out_trade_no"));
            System.out.println("交易金额: " + params.get("total_amount"));
            System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
            System.out.println("买家付款时间: " + params.get("gmt_payment"));
            System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

            // 更新订单未已支付[ORDER_PAID]
            orderMapper.updateState(Integer.valueOf(tradeNo), 1, payTime, alipayTradeNo);
        }
    }
    return "success";
}
```

支付成功后支付宝会调用本地此接口，这此接口支付宝会传进来很多参数，包含订单详情，支付宝流水号，支付款时间等等。

在此接口调用本地方法把订单状态改为已支付，即可完成此次支付操作。



# 9. 后台搭建

作为一个后台管理模块，需要一定的权限信息，只有给管理员方能访问后台接口，所以需要新建一张权限表。

![image-20221225201148241](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252011687.png)

对应的把用户与对应的权限进行关联，所以再新建一张表：

![image-20221225201241145](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252012498.png)

由于本项目使用了springSecurity框架，对于授权相对比较简单。

## 9.1 用户登录

### 9.1.1 需求分析

如下图若登录如何无管理员权限则提示错误。用户为系统管理员方可进入系统。

![image-20221222185051162](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212221850516.png)

后台系统不提供用户注册功能，只提供管理员登录功能。当前端表单发起请求过程中，后端security根据当前登录用户查询其权限，并封装为`LoginUser`。

### 9.1.2 后端实现

①`UserDetailsService`的实现类添加对应判断权限方法：

```java
 @Override
@Transactional
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    // 方法引用
    queryWrapper.eq(StringUtils.isNotEmpty(username),User::getUsername,username);

    User user = userMapper.selectOne(queryWrapper);

    if (Objects.isNull(user)) {
        throw new UsernameNotFoundException("用户名或密码错误");
    }
    //判断用户是否被删除
    if (Objects.equals(user.getIsDelete(), IS_DELETED)) {
        throw new SystemException(AppHttpCodeEnum.USER_IS_DELETED);
    }

    log.info("数据库登录用户：{}",user);
    //TODO 查询角色权限
    List<String> permissions = userMapper.selectRoleByUid(user.getUid());
    if (!permissions.contains(ROLE_ADMIN)){
        //非管理员
        throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
    }

    log.info("当前登录用户：{}；拥有权限：{}",user.getUsername(),permissions);

    return new LoginUser(user,permissions);
}
```

如果登录的用户不具有系统管理员`ROLE_ADMIN`权限，则返回前端403，信息为无权限操作。前端路由添加判断，若非管理员或未登录则页面必须跳转到登录接口。

```js
// 权限验证不通过给出提示
if (res.code === 401 || res.code === 403) {
  ElementUI.Message({
    message: res.msg,
    type: 'error'
  });
  localStorage.removeItem("userInfo");
  localStorage.removeItem("_t");
  // window.location.reload();
  this.$router.replace("/login")
}
```



②如果登录的是管理员，则把权限封装进`LoginUser`，为了防止直接使用url访问会拿到数据。可以在配置中添加拦截器，添加鉴权拦截器。

LoginUser添加权限字段：

```java
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permissions;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    /**
     * authorities 不会被序列化到 redis
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    /**
     * 封装 permissions 权限信息
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (authorities != null) {
            return authorities;
        }
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

配置拦截器：

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();// 关闭csrf
    http
            //不通过Session获取SecurityContext
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // 对于登录接口、注册接口 允许匿名访问
            .antMatchers("/user/login").anonymous()
            //注销接口需要认证才能访问
            .antMatchers("/user/logout").authenticated()
            // 配置权限
            .antMatchers("/user/*").hasAuthority("ROLE_ADMIN")
            .antMatchers("/role/*").hasAuthority("ROLE_ADMIN")
            .antMatchers("/goods/*").hasAuthority("ROLE_ADMIN")
            // 除上面外的所有请求全部需要鉴权认证
            .anyRequest().authenticated();

    // 过滤器
    http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    // 配置异常处理器
    http.exceptionHandling()
            // 认证失败处理
            .authenticationEntryPoint(authenticationEntryPoint)
            // 授权失败
            .accessDeniedHandler(accessDeniedHandler);

    //关闭默认的注销功能
    http.logout().disable();
    //允许跨域
    http.cors();
}
```

登录接口的业务层方法：

登陆成功后，缓存`LoginUser`到redis。

```java
/**
 * 登录
 *
 * @param user
 * @return ResponseResult.okResult(userLoginVo)
 */
@Override
public ResponseResult login(User user) {
    //判断用户名是否为空
    if (StringUtils.isEmpty(user.getUsername())) {
        throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
    }
    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    //判断是否认证通过
    if (Objects.isNull(authentication)) {
        throw new RuntimeException("用户名或密码错误");
    }
    // 认证成功，从Authentication获取LoginUser
    LoginUser loginUser = (LoginUser) authentication.getPrincipal();

    log.info("loginUser:{}", loginUser);

    String userId = loginUser.getUser().getUid().toString();
    // 生成token
    String jwt = JwtUtil.createJWT(userId);
    // 存入redis
    redisCache.setCacheObject(LOGIN_ADMIN_KEY + userId, loginUser);

    UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
    UserLoginVo userLoginVo = new UserLoginVo(jwt, userInfoVo);

    log.info("用户以登陆==>{}", userLoginVo);

    return ResponseResult.okResult(userLoginVo);
}
```



登陆成功来到用户管理：本后台系统已上传至服务器，不方便提供url

![image-20221225201342255](z-img/image-20221225201342255.png)

## 9.2 用户管理

### 9.2.1 需求分析

需要分页查询系统所有用户，并可进行模糊搜索。

然后就是添加用户、编辑用户、删除用户、批量删除用户功能。

### 9.2.2 后端实现

①以上全部接口需要管理员权限，前面配置类已配置过。接口一次性给出如下：

```java
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/username/{username}")
    public ResponseResult userInfo(@PathVariable String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return ResponseResult.okResult(userService.getOne(queryWrapper));
    }

    /**
     * 根据uid查询用户信息
     *
     * @param uid
     * @return
     */
    @GetMapping("/{uid}")
    public ResponseResult findOne(@PathVariable Integer uid) {
        return ResponseResult.okResult(userService.getUserInfo(uid));
    }
    /**
     * 新增或者更新
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addUser(@RequestBody User user) {
        return ResponseResult.okResult(userService.saveOrUpdateUser(user));
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public ResponseResult updateUser(@RequestBody User user) {
        return ResponseResult.okResult(userService.updateUserInfo(user));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String username,
                                   @RequestParam(defaultValue = "") String phone,
                                   @RequestParam(defaultValue = "") String email) {
        return ResponseResult.okResult(userService.userRolePage(pageNum,pageSize,username,phone,email));
    }

    /**
     * 把用户设置为删除状态
     *
     * @param uid
     * @return
     */
    @DeleteMapping("/{uid}")
    public ResponseResult setDeleted(@PathVariable Integer uid) {
        return ResponseResult.okResult(userService.setDeletedByUid(uid));
    }

    /**
     * 根据用户id删除
     *
     * @param uid
     * @return
     */
    @DeleteMapping("/del/{uid}")
    public ResponseResult deleteUser(@PathVariable Integer uid) {
        return ResponseResult.okResult(userService.removeById(uid));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        return ResponseResult.okResult(userService.removeByIds(ids));
    }

}
```

②业务层

```java
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfoVo getUserInfo(Integer uid) {
        //根据用户id查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(uid), User::getUid, uid);
        User user = getOne(queryWrapper);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return vo;
    }

    @Override
    public boolean updateUserInfo(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, user.getUid());
        return update(user, queryWrapper);
    }

    @Override
    public Integer setDeletedByUid(Integer uid) {
        return userMapper.setDeletedByUid(uid);

    }

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Page<UserRoleVo> userRolePage(Integer pageNum, Integer pageSize, String username, String phone, String email) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(username), User::getUsername, username);
        queryWrapper.like(Strings.isNotEmpty(phone), User::getUsername, phone);
        queryWrapper.like(Strings.isNotEmpty(email), User::getUsername, email);
        Page<User> userPage = page(new Page<>(pageNum, pageSize), queryWrapper);

        List<User> userList = userPage.getRecords();

        List<UserRoleVo> orderGoodVoList = new ArrayList<>();
        //封装用户权限
        userList.forEach(u -> {
            Integer uid = u.getUid();
            List<Role> userRoleList = roleMapper.getUserRoleList(uid);
            UserRoleVo userRoleVo = BeanCopyUtils.copyBean(u, UserRoleVo.class);
            userRoleVo.setRoleList(userRoleList);
            orderGoodVoList.add(userRoleVo);
        });

        Page<UserRoleVo> userRoleVoPage = new Page<>();
        userRoleVoPage.setCurrent(userPage.getCurrent());
        userRoleVoPage.setPages(userPage.getPages());
        userRoleVoPage.setSize(userPage.getSize());
        userRoleVoPage.setTotal(userPage.getTotal());

        userRoleVoPage.setRecords(orderGoodVoList);

        return userRoleVoPage;
    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean saveOrUpdateUser(User user) {

        //密码加密
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userMapper.insertUser(user);

        System.out.println(user);
        Integer uid = user.getUid();
        log.info("新增加的用户==>{}",uid);
        //增加普通权限
//        userMapper.insertUserRole(uid,2);

        return true;
    }
}
```

## 9.3 商品管理

### 9.3.1 需求分析

![image-20221225201445466](https://gcore.jsdelivr.net/gh/roydonGuo/Typora-Pic/md-pic202212252014950.png)

对数据库中商品表进行操作，包括基本CRUD。

### 9.3.2 后端实现

①控制层接口：

在控制层直接使用MP条件构造器进行操作简短省事整洁。

```java
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 分页查询所有商品
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public ResponseResult selectAll(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String title) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Goods::getTitle,title);
        lambdaQueryWrapper.orderByDesc(Goods::getPriority);
        return ResponseResult.okResult(goodsService.page(new Page<>(pageNum, pageSize),lambdaQueryWrapper));
    }

    /**
     * 新增或者更新
     *
     * @param goods
     * @return
     */
    @PostMapping
    public ResponseResult addUser(@RequestBody Goods goods) {
        return ResponseResult.okResult(goodsService.saveOrUpdate(goods));
    }

    /**
     * 根据gid删除
     *
     * @param gid
     * @return
     */
    @DeleteMapping("/del/{gid}")
    public ResponseResult deleteUser(@PathVariable Integer gid) {
        return ResponseResult.okResult(goodsService.removeById(gid));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        return ResponseResult.okResult(goodsService.removeByIds(ids));
    }
}
```


开发暂时告一段落......

