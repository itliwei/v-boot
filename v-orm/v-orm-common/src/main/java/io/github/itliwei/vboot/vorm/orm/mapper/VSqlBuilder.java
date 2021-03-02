package io.github.itliwei.vboot.vorm.orm.mapper;

import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.d.DeleteParam;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;
import io.github.itliwei.vboot.vorm.orm.u.UpdateParam;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.itliwei.vboot.vorm.orm.mapper.SqlBuilderUtil.*;

/**
 * Created by liwei on 17/8/23.
 */
public class VSqlBuilder {

    private static final Logger logger = LoggerFactory.getLogger(VSqlBuilder.class);

    public String insert(IdEntity obj) {
        Map<String, Class<?>> clazzMap = new HashMap<>();
        clazzMap.put("", obj.getClass());
        initEntities(clazzMap);
        String tableName = findTableName(obj.getClass());
        BEGIN();
        INSERT_INTO(tableName);
        VALUES(obj);
        return SQL();
    }

    public String update(Map<String, Object> parameter) {
        UpdateParam param = (UpdateParam) parameter.get("param");
        if (CollectionUtils.isEmpty(param.getConditionList())){
            logger.error("update conditions must hava value");
            throw new RuntimeException("update conditions must hava value");
        }
        Map<String, Class<?>> clazzMap = new HashMap<>();
        clazzMap.put("", param.getClazz());
        initEntities(clazzMap);
        String tableName = findTableName(param.getClazz());
        BEGIN();
        UPDATE(tableName);
        if (param.isAppointUpdate()) {
            SET(param.getFieldList(), param.getEntity());
        } else {
            SET(param.getEntity(), param.isUseNull());
        }
        if (param.getConditionList() != null) {
            where(param.getConditionList());
        }
        return SQL();
    }

    public String delete(Map<String, Object> parameter) {
        DeleteParam param = (DeleteParam) parameter.get("param");
        if (CollectionUtils.isEmpty(param.getConditionList())){
            logger.error("delete conditions must hava value");
            throw new RuntimeException("delete conditions must hava value");
        }
        Map<String, Class<?>> clazzMap = new HashMap<>();
        clazzMap.put("", param.getClazz());
        initEntities(clazzMap);
        String tableName = findTableName(param.getClazz());
        BEGIN();
        DELETE_FROM(tableName);
        if (param.getConditionList() != null) {
            where(param.getConditionList());
        }
        return SQL();
    }

    public String selectMapList(Map<String, Object> parameter) {
        SelectParam param = (SelectParam) parameter.get("param");
        Map<String, Class<?>> clazzMap = param.getClazzMap();
        initEntities(clazzMap);
        List<String> tableNameList = findTableNameList();
        BEGIN();
        if (param.isSelectMap()) {
            select(param.getFieldList());
        } else {
            selectAll();
        }
        FORM(tableNameList);
        if (param.getConditionList() != null) {
            where(param.getConditionList());
        }
        if (param.getOrderByList() != null) {
            orderBy(param.getOrderByList());
        }

        return SQL();
    }

    public String selectCount(Map<String, Object> parameter) {
        SelectParam param = (SelectParam) parameter.get("param");
        Map<String, Class<?>> clazzMap = param.getClazzMap();
        initEntities(clazzMap);
        List<String> tableNameList = findTableNameList();
        BEGIN();
        selectCountX();
        FORM(tableNameList);
        if (param.getConditionList() != null) {
            where(param.getConditionList());
        }
        return SQL();
    }

    public String selectSum(Map<String, Object> parameter) {
        SelectParam param = (SelectParam) parameter.get("param");
        Map<String, Class<?>> clazzMap = param.getClazzMap();
        initEntities(clazzMap);
        List<String> tableNameList = findTableNameList();
        BEGIN();
        selectSumX(param.getFieldList());
        FORM(tableNameList);
        if (param.getConditionList() != null) {
            where(param.getConditionList());
        }
        return SQL();
    }
}
