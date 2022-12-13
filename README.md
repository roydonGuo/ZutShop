# ZutShop
Junior practical training project

---

# 0. 项目介绍

项目名称：学子商城。类似于购物车系统、订单系统。前后端分离。

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

![image-20221213094711373](z-img/image-20221213094711373.png)

sql文件在项目resources中。

# 2. 项目准备

本项目使用springboot框架进行开发，ajax进行前后端交互。所以，涉及到的技术栈有：

* springboot
* springSecurity
* maven
* ajax、jQ
* redis

* mybatis-plus

## 2.1 创建maven项目

采用业务分离开发

![image-20221213100137023](z-img/image-20221213100137023.png)

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
                .description("学子商城")
                .contact(contact)   // 联系方式
                .version("1.0.0")  // 版本
                .build();
    }
}
```

启动类添加注解`@EnableSwagger2`启动Swagger

项目启动访问`http://localhost:7777/swagger-ui.html`即可

具体使用可参考官方网站，或前往：[http://c.biancheng.net/view/5532.html](http://c.biancheng.net/view/5532.html)

![image-20221213205613086](z-img/image-20221213205613086.png)

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

。LoginService

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

![image-20221213214122124](z-img/image-20221213214122124.png)

redis也成功存入数据

![image-20221213214223209](z-img/image-20221213214223209.png)

### 3.1.2 登出

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

![image-20221213214640730](z-img/image-20221213214640730.png)

### 3.2.1 需求分析

前端发送ajax请求，确认密码由前端进行判断，前端只传给后端用户名、密码两个字段的json串。

* 前端传入数据格式：(json){ 'username': username, 'password': password }

* 请求地址：/uesr/register

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

![image-20221213220239933](z-img/image-20221213220239933.png)

### 3.3.1 需求分析





