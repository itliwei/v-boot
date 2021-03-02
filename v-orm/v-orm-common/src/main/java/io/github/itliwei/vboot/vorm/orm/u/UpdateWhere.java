package io.github.itliwei.vboot.vorm.orm.u;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;

/**
 * Created by liwei on 17/8/9.
 */
public class UpdateWhere implements UpdateExec {

    public UpdateWhere and(Condition condition) {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        param.addCondition(condition);
        return this;
    }
}
