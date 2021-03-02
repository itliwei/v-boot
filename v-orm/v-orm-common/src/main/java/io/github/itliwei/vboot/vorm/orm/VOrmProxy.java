package io.github.itliwei.vboot.vorm.orm;

import io.github.itliwei.vboot.vorm.orm.c.Insert;
import io.github.itliwei.vboot.vorm.orm.d.Delete;
import io.github.itliwei.vboot.vorm.orm.d.DeleteParam;
import io.github.itliwei.vboot.vorm.orm.r.Select;
import io.github.itliwei.vboot.vorm.orm.r.SelectMulti;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;
import io.github.itliwei.vboot.vorm.orm.u.Update;
import io.github.itliwei.vboot.vorm.orm.u.UpdateParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by liwei on 17/9/28.
 */
public class VOrmProxy<T extends IdEntity> {

    private static final Logger logger = LoggerFactory.getLogger(VOrmProxy.class);

    /**
     * 单表插入数据
     * @param clazz 对应实体class
     */
    public Insert<T> insert(Class<T> clazz) {
        if (logger.isDebugEnabled()) {
            logger.debug("insert into class {}.", clazz);
        }
        return new Insert<>();
    }

    /**
     * 单表更新数据
     * @param clazz 对应实体class
     */
    public Update<T> update(Class<T> clazz) {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        if (param == null) {
            param = new UpdateParam();
            VOrmContext.UPDATE_PARAM_THREAD_LOCAL.set(param);
        }
        param.setClazz(clazz);
        return new Update<>();
    }

    /**
     * 单表删除数据
     * @param clazz 对应实体class
     */
    public Delete delete(Class<T> clazz) {
        DeleteParam param = VOrmContext.DELETE_PARAM_THREAD_LOCAL.get();
        if (param == null) {
            param = new DeleteParam();
            VOrmContext.DELETE_PARAM_THREAD_LOCAL.set(param);
        }
        param.setClazz(clazz);
        return new Delete();
    }

    /**
     * 单表查询数据
     * @param clazz 对应实体class
     */
    public Select<T> select(Class<T> clazz) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        if (param == null) {
            param = new SelectParam();
            VOrmContext.SELECT_PARAM_THREAD_LOCAL.set(param);
        }
        param.setResultType(clazz);
        param.addClazzMap(clazz, "");
        return new Select<>();
    }

    /**
     * 多表查询数据
     * @param clazz 对应实体class
     * @param alias 对应实体别名
     */
    public SelectMulti selectMulti(Class<? extends IdEntity> clazz, String alias) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        if (param == null) {
            param = new SelectParam();
            VOrmContext.SELECT_PARAM_THREAD_LOCAL.set(param);
        }
        param.setResultType(Map.class);
        param.addClazzMap(clazz, alias);
        return new SelectMulti();
    }
}
