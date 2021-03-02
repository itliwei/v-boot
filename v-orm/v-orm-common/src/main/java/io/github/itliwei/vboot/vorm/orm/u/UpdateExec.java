package io.github.itliwei.vboot.vorm.orm.u;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;

/**
 * 提交操作
 * Created by liwei on 17/8/11.
 */
public interface UpdateExec {

    default int exec() {
        UpdateParam param = VOrmContext.UPDATE_PARAM_THREAD_LOCAL.get();
        return VOrmContext.LOCAL_MAPPER.get().update(param);
    }
}
