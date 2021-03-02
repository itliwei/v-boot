package io.github.itliwei.vboot.vorm.orm.r;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.OrderBy;
import io.github.itliwei.vboot.vorm.orm.result.ListResult;
import io.github.itliwei.vboot.vorm.orm.result.OneResult;

/**
 * Created by liwei on 17/8/15.
 */
public class OrderByOpt<T> implements OneResult<T>, ListResult<T> {

    public OrderByOpt<T> orderBy(OrderBy orderBy) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addOrderBy(orderBy);
        return this;
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
