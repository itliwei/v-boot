package io.github.itliwei.vboot.vorm.orm;

import io.github.itliwei.vboot.vorm.orm.c.Insert;
import io.github.itliwei.vboot.vorm.orm.d.Delete;
import io.github.itliwei.vboot.vorm.orm.d.DeleteParam;
import io.github.itliwei.vboot.vorm.orm.mapper.VMapper;
import io.github.itliwei.vboot.vorm.orm.r.Select;
import io.github.itliwei.vboot.vorm.orm.r.SelectMulti;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;
import io.github.itliwei.vboot.vorm.orm.u.Update;
import io.github.itliwei.vboot.vorm.orm.u.UpdateParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Corm 使用API
 * Created by liwei on 17/8/9.
 */
public class VOrm {
    private static final Logger logger = LoggerFactory.getLogger(VOrm.class);

    /**
     * 单表插入数据
     * @param clazz 对应实体class
     */
    public static <T extends IdEntity> Insert<T> insert(Class<T> clazz) {
        VOrmContext.LOCAL_MAPPER.set(VOrmContext.getVMapper(true));
        return new VOrmProxy<T>().insert(clazz);
    }

    /**
     * 单表更新数据
     * @param clazz 对应实体class
     */
    public static <T extends IdEntity> Update<T> update(Class<T> clazz) {
        VOrmContext.UPDATE_PARAM_THREAD_LOCAL.set(new UpdateParam());
        VOrmContext.LOCAL_MAPPER.set(VOrmContext.getVMapper(true));
        return new VOrmProxy<T>().update(clazz);
    }

    /**
     * 单表删除数据
     * @param clazz 对应实体class
     */
    public static <T extends IdEntity> Delete delete(Class<T> clazz) {
        VOrmContext.DELETE_PARAM_THREAD_LOCAL.set(new DeleteParam());
        VOrmContext.LOCAL_MAPPER.set(VOrmContext.getVMapper(true));
        return new VOrmProxy<T>().delete(clazz);
    }

    /**
     * 单表查询数据
     * @param clazz 对应实体class
     */
    public static <T extends IdEntity> Select<T> select(Class<T> clazz) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.set(new SelectParam());
        VOrmContext.LOCAL_MAPPER.set(VOrmContext.getVMapper());
        return new VOrmProxy<T>().select(clazz);
    }

    /**
     * 多表查询数据
     * @param clazz 对应实体class
     * @param alias 对应实体别名
     */
    public static SelectMulti selectMulti(Class<? extends IdEntity> clazz, String alias) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.set(new SelectParam());
        VOrmContext.LOCAL_MAPPER.set(VOrmContext.getVMapper());
        return new VOrmProxy<>().selectMulti(clazz, alias);
    }

    /**
     * 指定master操作数据
     * 在事务中，需要调用该方法，强制指定到master上操作
     */
    public static void master() {
        VOrmContext.IS_MASTER.set(true);
    }

    /**
     * 释放指定master操作
     * 在事务方法调用完成后，调用commit结束
     */
    public static void commit() {
        VOrmContext.IS_MASTER.remove();
    }

    public static <T extends IdEntity> VOrmProxy<T> switchM(VMapper mapper) {
        VOrmContext.UPDATE_PARAM_THREAD_LOCAL.set(null);
        VOrmContext.DELETE_PARAM_THREAD_LOCAL.set(null);
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.set(null);
        VOrmContext.LOCAL_MAPPER.set(mapper);
        return new VOrmProxy<T>();
    }
}
