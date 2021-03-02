package io.github.itliwei.vboot.vorm.orm;

import io.github.itliwei.vboot.vorm.orm.d.DeleteParam;
import io.github.itliwei.vboot.vorm.orm.mapper.VMapper;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;
import io.github.itliwei.vboot.vorm.orm.u.UpdateParam;

import java.util.List;
import java.util.Random;

/**
 * Corm 上下文环境
 * Created by liwei on 17/9/8.
 */
public class VOrmContext {
    /**
     * 查询数据参数
     */
    public static final ThreadLocal<SelectParam> SELECT_PARAM_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 更新数据参数
     */
    public static final ThreadLocal<UpdateParam> UPDATE_PARAM_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 删除数据参数
     */
    public static final ThreadLocal<DeleteParam> DELETE_PARAM_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 强制使用master的标识
     */
    public static final ThreadLocal<Boolean> IS_MASTER = new ThreadLocal<>();

    public static final ThreadLocal<VMapper> LOCAL_MAPPER = new ThreadLocal<>();

    static final NullableImmutableHolder<List<VMapper>> masterMapperList = new NullableImmutableHolder<>();

    static final NullableImmutableHolder<List<VMapper>> slaveMapperList = new NullableImmutableHolder<>();

    /**
     * 查询获取一个CMapper
     */
    public static VMapper getVMapper() {

        List<VMapper> mapperList;
        // 1.如果设置了IS_MASTER=true会使用master mapper
        // 2.如果没有设置IS_MASTER，但是设置了SelectParam.userMaster=true会使用master mapper
        if ((IS_MASTER.get() != null && IS_MASTER.get()) ||
                (IS_MASTER.get() == null && SELECT_PARAM_THREAD_LOCAL.get().isUseMaster())) {
            mapperList = masterMapperList.get();
        } else {
            mapperList = slaveMapperList.get();
            // 如果没有配置slave mapper就使用master mapper
            if (mapperList == null || mapperList.isEmpty()) {
                mapperList = masterMapperList.get();
            }
        }

        return getVMapper(mapperList);
    }

    /**
     * 指定主从获取一个CMapper
     * @param useMaster 是否使用master
     */
    public static VMapper getVMapper(boolean useMaster) {
        if (useMaster) {
            return getVMapper(masterMapperList.get());
        } else {
            return getVMapper();
        }
    }

    private static VMapper getVMapper(List<VMapper> mapperList) {
        if (mapperList == null || mapperList.isEmpty()) {
            throw new RuntimeException("mapper not set.");
        }
        // 随机获取mapper list中的一条mapper数据
        return mapperList.get(new Random().nextInt(mapperList.size()));
    }
}
