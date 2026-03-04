package com.rescue.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.rescue.framework.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatis Plus 元对象处理器
 * 自动填充创建时间、更新时间、创建人、更新人
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        extractedCreate(metaObject);
        extractedUpdate(metaObject);
    }

    /**
     * 更新填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        extractedUpdate(metaObject);
    }

    /**
     * 填充创建相关字段
     *
     * @param metaObject 元对象
     */
    private void extractedCreate(MetaObject metaObject) {
        // 填充创建时间
        boolean hasCreateTime = metaObject.hasGetter("createTime");
        Object createTimeVal = getFieldValByName("createTime", metaObject);
        if (hasCreateTime && createTimeVal == null) {
            this.strictInsertFill(
                    metaObject,
                    "createTime",
                    LocalDateTime::now,
                    LocalDateTime.class
            );
        }

        // 填充创建人
        boolean hasCreateBy = metaObject.hasGetter("createBy");
        Object createByVal = getFieldValByName("createBy", metaObject);
        if (hasCreateBy && createByVal == null) {
            Long userId = SecurityUtils.getUserId();
            if (userId != null) {
                this.strictInsertFill(
                        metaObject,
                        "createBy",
                        () -> userId,
                        Long.class
                );
            }
        }
    }

    /**
     * 填充更新相关字段
     *
     * @param metaObject 元对象
     */
    private void extractedUpdate(MetaObject metaObject) {
        // 填充更新时间
        boolean hasUpdateTime = metaObject.hasGetter("updateTime");
        Object updateTimeVal = getFieldValByName("updateTime", metaObject);
        if (hasUpdateTime && updateTimeVal == null) {
            // 注意：这里根据操作类型自动选择合适的方法
            if (metaObject.hasSetter("createTime")) {
                // insert 操作
                this.strictInsertFill(
                        metaObject,
                        "updateTime",
                        LocalDateTime::now,
                        LocalDateTime.class
                );
            } else {
                // update 操作
                this.strictUpdateFill(
                        metaObject,
                        "updateTime",
                        LocalDateTime.class,
                        LocalDateTime.now()
                );
            }
        }

        // 填充更新人
        boolean hasUpdateBy = metaObject.hasGetter("updateBy");
        Object updateByVal = getFieldValByName("updateBy", metaObject);
        if (hasUpdateBy && updateByVal == null) {
            Long userId = SecurityUtils.getUserId();
            if (userId != null) {
                if (metaObject.hasSetter("createBy")) {
                    // insert 操作
                    this.strictInsertFill(
                            metaObject,
                            "updateBy",
                            () -> userId,
                            Long.class
                    );
                } else {
                    // update 操作
                    this.strictUpdateFill(
                            metaObject,
                            "updateBy",
                            Long.class,
                            userId
                    );
                }
            }
        }
    }
}