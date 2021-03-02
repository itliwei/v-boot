package io.github.itliwei.vboot.vorm.orm.result;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.utils.BeanUtil;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * ListResult
 * Created by liwei on 17/8/23.
 */
public interface ListResult<T> {

    @SuppressWarnings("unchecked")
    default List<T> list() {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        List<Map<String, Object>> result = VOrmContext.LOCAL_MAPPER.get().selectMapListPage(param, param.getSkipped());
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        Class<?> type = param.getResultType();
        if (type == Map.class) {
            return (List<T>) result;
        }
        return BeanUtil.entityList((Class<T>) type, result);
    }
}
