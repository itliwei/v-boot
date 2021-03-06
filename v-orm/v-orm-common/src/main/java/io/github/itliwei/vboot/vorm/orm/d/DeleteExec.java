package io.github.itliwei.vboot.vorm.orm.d;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;

/**
 * 删除数据提交操作
 * Created by liwei on 17/9/26.
 */
public interface DeleteExec {

    /**
     * 执行数据库操作
     * @return 操作数
     */
    default int exec() {
        return VOrmContext.LOCAL_MAPPER.get().delete(VOrmContext.DELETE_PARAM_THREAD_LOCAL.get());
    }
}
