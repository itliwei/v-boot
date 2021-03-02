package io.github.itliwei.vboot.vorm.orm.u;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;

/**
 * Created by liwei on 17/9/28.
 */
public class UseNullOpt<T extends IdEntity> {
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
}
