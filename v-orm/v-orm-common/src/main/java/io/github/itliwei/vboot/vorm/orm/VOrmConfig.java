package io.github.itliwei.vboot.vorm.orm;

import io.github.itliwei.vboot.vorm.orm.mapper.VMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Corm 配置
 * 主要配置CMapper对象
 * Created by liwei on 17/9/3.
 */
public class VOrmConfig {

    public void addDefaultMasterMapper(VMapper mapper) {
        List<VMapper> mapperList = VOrmContext.masterMapperList.get();
        if (mapperList == null) {
            mapperList = new ArrayList<>();
            VOrmContext.masterMapperList.set(mapperList);
        }
        mapperList.add(mapper);
    }

    public void addDefaultSlaveMapper(VMapper mapper) {
        List<VMapper> mapperList = VOrmContext.slaveMapperList.get();
        if (mapperList == null) {
            mapperList = new ArrayList<>();
            VOrmContext.slaveMapperList.set(mapperList);
        }
        mapperList.add(mapper);
    }
}
