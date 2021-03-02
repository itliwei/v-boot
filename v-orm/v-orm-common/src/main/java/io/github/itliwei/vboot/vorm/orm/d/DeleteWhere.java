package io.github.itliwei.vboot.vorm.orm.d;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;

/**
 * Created by liwei on 17/9/26.
 */
public class DeleteWhere implements DeleteExec {

    public DeleteWhere and(Condition condition) {
        VOrmContext.DELETE_PARAM_THREAD_LOCAL.get().addCondition(condition);
        return this;
    }
}
