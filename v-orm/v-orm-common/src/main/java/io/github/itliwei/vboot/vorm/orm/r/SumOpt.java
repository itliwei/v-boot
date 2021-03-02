package io.github.itliwei.vboot.vorm.orm.r;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;
import io.github.itliwei.vboot.vorm.orm.result.SumResult;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by liwei on 17/8/23.
 */
public class SumOpt implements SumResult {

    public SumWhere where(Condition condition) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(condition);
        return this.new SumWhere();
    }

    public SumWhere where(Collection<Condition> conditions) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(conditions);
        return this.new SumWhere();
    }

    public SumWhere where(Condition ... conditions) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(Arrays.asList(conditions));
        return this.new SumWhere();
    }

    public class SumWhere implements SumResult {
        public SumWhere and(Condition condition) {
            VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(condition);
            return this;
        }
    }
}
