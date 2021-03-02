package io.github.itliwei.vboot.vorm.orm.result;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;

import java.util.Map;

/**
 * SumResult
 * Created by liwei on 17/8/23.
 */
public interface SumResult {

    default Number number() {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        Map<String, Object> result = VOrmContext.LOCAL_MAPPER.get().selectSum(param);
        if (result == null || result.isEmpty()) {
            return 0;
        }
        return (Number) result.entrySet().iterator().next().getValue();
    }
}
