package io.github.itliwei.vboot.vorm.orm.u;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by liwei on 17/10/25.
 */
public class WhereOpt {

    public UpdateWhere where(Condition condition) {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        param.addCondition(condition);
        return new UpdateWhere();
    }

    public UpdateWhere where(Collection<Condition> conditions) {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        param.addCondition(conditions);
        return new UpdateWhere();
    }

    public UpdateWhere where(Condition ... conditions) {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        param.addCondition(Arrays.asList(conditions));
        return new UpdateWhere();
    }
}
