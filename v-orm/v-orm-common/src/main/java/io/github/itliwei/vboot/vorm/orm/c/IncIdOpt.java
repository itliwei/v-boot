package io.github.itliwei.vboot.vorm.orm.c;

import io.github.itliwei.vboot.vorm.orm.VOrmContext;
import io.github.itliwei.vboot.vorm.orm.IdEntity;

/**
 * Created by liwei on 17/9/23.
 */
public class IncIdOpt<T extends IdEntity> {
    public int obj(T t) {
        return VOrmContext.LOCAL_MAPPER.get().insertIncId(t);
    }
}
