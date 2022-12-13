package edu.zut.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import edu.zut.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

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