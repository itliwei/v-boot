package io.github.itliwei.vboot.vorm.orm.d;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;

import java.util.Arrays;
import java.util.Collection;

/**
 * Delete
 * Created by liwei on 17/8/9.
 */
public class Delete implements DeleteExec {

    public DeleteWhere where(Condition condition) {
        VOrmContext.DELETE_PARAM_THREAD_LOCAL.get().addCondition(condition);
        return new DeleteWhere();
    }

    public DeleteWhere where(Collection<Condition> conditions) {
        VOrmContext.DELETE_PARAM_THREAD_LOCAL.get().addCondition(conditions);
        return new DeleteWhere();
    }

    public DeleteWhere where(Condition ... conditions) {
        VOrmContext.DELETE_PARAM_THREAD_LOCAL.get().addCondition(Arrays.asList(conditions));
        return new DeleteWhere();
    }
}
