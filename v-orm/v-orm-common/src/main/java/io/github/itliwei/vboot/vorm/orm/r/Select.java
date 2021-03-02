package io.github.itliwei.vboot.vorm.orm.r;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;
import io.github.itliwei.vboot.vorm.orm.opt.Field;
import io.github.itliwei.vboot.vorm.orm.opt.OrderBy;
import io.github.itliwei.vboot.vorm.orm.result.ListResult;
import io.github.itliwei.vboot.vorm.orm.result.OneResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * 单表查询
 * Created by liwei on 17/8/9.
 */
public class Select<T> implements OneResult<T>, ListResult<T> {

    public Select<T> master() {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().useMaster();
        return this;
    }

    /**
     * 指定字段查询
     * @param field 字段名
     */
    public SelectMap field(Field field) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        param.addField(field);
        param.setResultType(Map.class);
        return new SelectMap();
    }

    public SelectMap field(Collection<Field> fields) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        param.addField(fields);
        param.setResultType(Map.class);
        return new SelectMap();
    }

    public SelectMap field(Field ... fields) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        param.addField(Arrays.asList(fields));
        param.setResultType(Map.class);
        return new SelectMap();
    }

    /**
     * 查询 T 所有字段
     * @param condition 条件
     */
    public WhereOpt<T> where(Condition condition) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(condition);
        return new WhereOpt<>();
    }


    public WhereOpt<T> where(Collection<Condition> conditions) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(conditions);
        return new WhereOpt<>();
    }

    public WhereOpt<T> where(Condition ... conditions) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addCondition(Arrays.asList(conditions));
        return new WhereOpt<>();
    }

    public SumOpt sum(Field field) {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        param.addField(field);
        return new SumOpt();
    }

    public CountOpt count() {
        return new CountOpt();
    }

    public LimitOpt<T> limit(int skip, int size) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().limit(skip, size);
        return new LimitOpt<>();
    }

    public PageOpt<T> pageable(int pn, int pz) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().limit((pn - 1) * pz, pz);
        return new PageOpt<>();
    }

    public OrderByOpt<T> orderBy(OrderBy orderBy) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addOrderBy(orderBy);
        return new OrderByOpt<>();
    }

    public OrderByOpt<T> orderBy(Collection<OrderBy> orderBys) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addOrderBy(orderBys);
        return new OrderByOpt<>();
    }

    public OrderByOpt<T> orderBy(OrderBy ... orderBys) {
        VOrmContext.SELECT_PARAM_THREAD_LOCAL.get().addOrderBy(Arrays.asList(orderBys));
        return new OrderByOpt<>();
    }

}
