package io.github.itliwei.vboot.vorm.entity;



import io.github.itliwei.vboot.vorm.annotation.Entity;
import io.github.itliwei.vboot.vorm.annotation.Field;
import io.github.itliwei.vboot.vorm.annotation.Type;
import io.github.itliwei.vboot.vorm.annotation.controller.ControllerClass;
import io.github.itliwei.vboot.vorm.annotation.elementui.ElementClass;
import io.github.itliwei.vboot.vorm.annotation.query.Query;
import io.github.itliwei.vboot.vorm.annotation.query.QueryModel;
import io.github.itliwei.vboot.vorm.annotation.service.ServiceClass;
import io.github.itliwei.vboot.vorm.annotation.view.View;
import io.github.itliwei.vboot.vorm.annotation.view.ViewObject;
import io.github.itliwei.vboot.vorm.orm.IdEntity;
import io.github.itliwei.vboot.vorm.orm.annotation.Table;
import io.github.itliwei.vboot.vorm.orm.opt.Condition;
import lombok.*;

import static io.github.itliwei.vboot.vorm.entity.TestEntity.SIMPLE_DTO;
import static io.github.itliwei.vboot.vorm.entity.TestEntity.SIMPLE_VO;


/**
 * 用户表
 * Created by liwei on 19/6/14.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Type(label = "用户")
@Table(value = "tmp_user")
@QueryModel(name = "TestQueryModel")
@ServiceClass(name = "TestService")
@ControllerClass(path = "api/user",desc = "用户接口",name = "TestController")
@ElementClass
@ViewObject(groups = {SIMPLE_VO,SIMPLE_DTO})
public class TestEntity extends IdEntity {
    protected static final String SIMPLE_VO = "TestVo";
    protected static final String SIMPLE_DTO = "TestDto";

    @Field(label = "姓名")
    @Query(value = {Condition.Operator.eq, Condition.Operator.in})
    @View(groups = {SIMPLE_VO,SIMPLE_DTO})
    private String username;

    @Field(label = "jobId")
    @Query({Condition.Operator.eq, Condition.Operator.in})
    @View(groups = {SIMPLE_VO})
    private Long jobId;

    @Field(label = "年龄")
    @Query({Condition.Operator.eq, Condition.Operator.in})
    @View(groups = {SIMPLE_VO})
    private Integer age;

    @Field(label = "地址")
    @Query({Condition.Operator.eq, Condition.Operator.in})
    @View(groups = {SIMPLE_VO})
    private String address;

}
