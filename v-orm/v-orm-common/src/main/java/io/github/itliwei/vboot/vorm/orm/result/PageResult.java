package io.github.itliwei.vboot.vorm.orm.result;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.utils.BeanUtil;
import io.github.itliwei.vboot.vorm.orm.opt.Page;
import io.github.itliwei.vboot.vorm.orm.opt.Skipped;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * PageResult
 * Created by liwei on 17/8/23.
 */
public interface PageResult<T> {

    @SuppressWarnings("unchecked")
    default Page<T> page() {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        Skipped skipped = param.getSkipped();
        List<Map<String, Object>> result = VOrmContext.LOCAL_MAPPER.get().selectMapListPage(param, skipped);
        List<T> ts = null;
        if (result == null || result.isEmpty()) {
            ts = Collections.emptyList();
        } else {
            Class<?> type = param.getResultType();
            if (type == Map.class) {
                ts = (List<T>) result;
            } else {
                ts = BeanUtil.entityList((Class<T>) type, result);
            }
        }
        long count = VOrmContext.LOCAL_MAPPER.get().selectCount(param);
        return new Page<T>(ts, count, skipped.getSkip() / skipped.getCount() + 1, skipped.getCount());
    }
}
