package io.github.itliwei.vboot.vorm.mapper;

import io.github.itliwei.vboot.vorm.entity.TestEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : liwei
 * @date : 2021/03/08 22:35
 * @description : mapper文件
 */
public interface TestMapper {
    @Select("select * from tmp_user")
    List<TestEntity> selectAll();
}
