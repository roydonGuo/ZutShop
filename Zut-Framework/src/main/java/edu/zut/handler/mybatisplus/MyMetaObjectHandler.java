package edu.zut.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
//        Integer userId = null;
//        try {
//            userId = SecurityUtils.getUserId();
//        } catch (Exception e) {
//            e.printStackTrace();
//            userId = -1;//表示是自己创建
//        }
//        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("createBy",userId , metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName(" ", SecurityUtils.getUserId(), metaObject);
    }
}