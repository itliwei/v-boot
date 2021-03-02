package io.github.itliwei.vboot.vorm.orm.result;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;

/**
 * CountResult
 * Created by liwei on 17/8/23.
 */
public interface CountResult {

    default long number() {
        SelectParam param = VOrmContext.SELECT_PARAM_THREAD_LOCAL.get();
        return VOrmContext.LOCAL_MAPPER.get().selectCount(param);
    }
}
