package io.github.itliwei.vboot.vorm.orm.r;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;
import io.github.itliwei.vboot.vorm.orm.opt.OrderBy;
import io.github.itliwei.vboot.vorm.orm.result.ListResult;
import io.github.itliwei.vboot.vorm.orm.result.OneResult;

/**
 * Created by liwei on 17/8/23.
 */
public class WhereOpt<T> implements OneResult<T>, ListResult<T> {

    public WhereOpt<T> and(Condition condition) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(condition);
        return this;
    }

    public OrderByOpt<T> orderBy(OrderBy orderBy) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addOrderBy(orderBy);
        return new OrderByOpt<>();
    }

    public LimitOpt<T> limit(int skip, int size) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().limit(skip, size);
        return new LimitOpt<>();
    }

    public PageOpt<T> pageable(int pn, int pz) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().limit((pn - 1) * pz, pz);
        return new PageOpt<>();
    }
}
