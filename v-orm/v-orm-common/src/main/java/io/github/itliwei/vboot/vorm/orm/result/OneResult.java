package io.github.itliwei.vboot.vorm.orm.result;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.utils.BeanUtil;
import io.github.itliwei.vboot.vorm.orm.opt.Skipped;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;

import java.util.List;
import java.util.Map;

/**
 * OneResult
 * Created by liwei on 17/8/23.
 */
public interface OneResult<T> {

    @SuppressWarnings("unchecked")
    default T one() {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        List<Map<String, Object>> result = VOrmContext.LOCAL_MAPPER.get().selectMapListPage(param, new Skipped(0, 1));
        if (result == null || result.isEmpty()) {
            return null;
        }
        Class<?> type = param.getResultType();
        if (type == Map.class) {
            return (T) result.get(0);
        }
        return BeanUtil.hashToObject(result.get(0), (Class<T>) type);
    }
}
