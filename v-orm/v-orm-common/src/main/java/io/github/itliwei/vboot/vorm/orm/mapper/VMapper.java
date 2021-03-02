package io.github.itliwei.vboot.vorm.orm.mapper;

import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.d.DeleteParam;
import io.github.itliwei.vboot.vorm.orm.opt.Skipped;
import io.github.itliwei.vboot.vorm.orm.r.SelectParam;
import io.github.itliwei.vboot.vorm.orm.u.UpdateParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * CMapper
 * Created by liwei on 17/8/23.
 */
public interface VMapper {

    /**
     * 插入数据,实体对象必须是指定id的
     * @param t 实体对象,必须继承{@link IdEntity}
     * @param <T> 实体泛型
     * @return 操作数
     */
    @InsertProvider(type = VSqlBuilder.class, method = "insert")
    <T extends IdEntity> int insert(T t);

    /**
     * 插入数据,设置自增id（目前只适用于MySQL）
     * @param t 实体对象,必须继承{@link IdEntity}
     * @param <T> 实体泛型
     * @return 操作数
     */
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = long.class)
    @InsertProvider(type = VSqlBuilder.class, method = "insert")
    <T extends IdEntity> int insertIncId(T t);

    /**
     * 更新数据
     * @param param 更新参数
     * @return 操作数
     */
    @UpdateProvider(type = VSqlBuilder.class, method = "update")
    int update(@Param("param") UpdateParam param);

    /**
     * 删除数据
     * @param param 删除参数
     * @return 操作数
     */
    @DeleteProvider(type = VSqlBuilder.class, method = "delete")
    int delete(@Param("param") DeleteParam param);

    /**
     * 查询列表数据
     * @param param 查询参数
     * @param skipped skip
     * @return list
     */
    @SelectProvider(type = VSqlBuilder.class, method = "selectMapList")
    List<Map<String, Object>> selectMapListPage(@Param("param") SelectParam param, @Param("skipped") Skipped skipped);

    /**
     * 查询条件下的数据条数
     * @param param 查询参数
     * @return num
     */
    @SelectProvider(type = VSqlBuilder.class, method = "selectCount")
    long selectCount(@Param("param") SelectParam param);

    /**
     * 查询sum函数
     * @param param 查询参数
     * @return sum
     */
    @SelectProvider(type = VSqlBuilder.class, method = "selectSum")
    Map<String, Object> selectSum(@Param("param") SelectParam param);
}
