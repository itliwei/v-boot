package io.github.itliwei.vboot.vorm.orm.r;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.opt.Field;

import java.util.Collection;

/**
 * Created by liwei on 17/8/11.
 */
public class SelectMulti {

    public SelectMulti entity(Class<? extends IdEntity> clazz, String alias) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        param.addClazzMap(clazz, alias);
        return this;
    }

    public SelectMap field(Field field) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        param.addField(field);
        return new SelectMap();
    }

    public SelectMap field(Collection<Field> fields) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        param.addField(fields);
        return new SelectMap();
    }

    public SumOpt sum(Field field) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        param.addField(field);
        return new SumOpt();
    }

    public CountOpt count() {
        return new CountOpt();
    }
}
