package io.github.itliwei.vboot.vorm.orm.r;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;
import io.github.itliwei.vboot.vorm.orm.result.CountResult;

/**
 * Created by liwei on 17/9/18.
 */
public class CountOpt implements CountResult {

    public CountWhere where(Condition condition) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(condition);
        return new CountWhere();
    }

    public class CountWhere implements CountResult {
        public CountWhere and(Condition condition) {
            VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(condition);
            return this;
        }
    }

}
