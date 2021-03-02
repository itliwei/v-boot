package io.github.itliwei.vboot.vorm.orm.u;


import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;
import io.github.itliwei.vboot.vorm.orm.opt.Field;


/**
 * Created by liwei on 17/8/9.
 */
public class Update<T extends IdEntity> {

    public AppointUpdate<T> field(Field field, Object value) {
        AppointUpdate.setField(field, value);
        return new AppointUpdate<>();
    }

    public int obj(T t) {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        param.setEntity(t);
        param.addCondition(Condition.eq(IdEntity.ID_PN, t.getId()));
        return VOrmContext.LOCAL_MAPPER.get().update(param);
    }

    public WhereOpt entity(T t) {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        param.setEntity(t);
        param.addCondition(Condition.eq(IdEntity.ID_PN, t.getId()));
        return new WhereOpt();
    }

    public UseNullOpt<T> useNull() {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        param.setUseNull(true);
        return new UseNullOpt<T>();
    }

}
