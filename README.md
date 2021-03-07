# v-boot

#### 介绍

v-boot是一个微服务框架，旨在为微服务盛行的今天提供一些简单、易用、常用的开发框架工具，以此来提高工作效率，提高代码规范，降低维护成本
#### 核心组建
核心组建介绍
1.  ORM框架 [v-orm](https://github.com/itliwei/v-boot/tree/master/v-orm)

    v-orm框架是在mybatis之上，做了定制化封装，无需更多使用配置，遵照DDD领域驱动设计。较之于mybatis plus 更易用，更简洁，可分钟级别自动代码生成controller、service、vo、dto、entity等，以及elementui的页面，简化开发流程。学习和使用成本更低。
    核心功能：
    
        1、ORM框架：无需关注底层SQL逻辑
        2、使用简单：和mybatis使用完全一致，没有其他使用成本
        3、代码规范：统一的代码结构、接口格式、响应编码、异常信息编码，用起来更规范
        4、代码生成：可分钟级别自动代码生成controller、service、vo、dto、entity等，以及elementui的页面。
        5、功能灵活：提供基础VService可builder模式灵活查询任意数据，满足更多定制化需求
        6、支持主从切换（暂不支持分库分表）
        7、支持多数据源
        8、支持多种数据库（mysql、oracle）
                
2.  基础工具 [v-tool](https://github.com/itliwei/v-boot/tree/master/v-tool)

#### VORM使用教程

使用参考： [v-orm-spring-boot-demo](https://github.com/itliwei/v-boot/tree/master/v-orm/v-orm-spring-boot-demo)

1.  引入starter

        <dependency>
            <groupId>io.github.itliwei.vboot.vorm</groupId>
            <artifactId>v-orm-spring-boot-starter</artifactId>
            <version>XXXX-RELEASE</version>
        </dependency>
            
2.  编辑实体类

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
    @ViewObject(groups = {TestEntity.SIMPLE_VO,TestEntity.SIMPLE_DTO})
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
    
    @Table：生成具体数据库表名称
    
    @QueryModel：生成对应的查询对象
    
    @ServiceClass：生成对应的Sercvice类
    
    @ControllerClass：生成对应的Controller类
    
    @ElementClass 生成对应的Elementui页面
    
    @ViewObject：生成对应的DTO，VO
    
        @Field：生成对应数据库的注释
        @Query：是指定字段的查询条件
        @View：是字段在不在DTO，VO里生成
    
    
3.  编写生成工具代码
    
        import io.github.itliwei.vboot.vorm.entity.TestEntity;
        import io.github.itliwei.vboot.vorm.generator.Config;
        import io.github.itliwei.vboot.vorm.generator.Generator;
        import io.github.itliwei.vboot.vorm.generator.handler.*;
        
        import java.nio.file.Paths;
        
        /**
         * @author : liwei
         * @date : 2021/03/06 21:50
         */
        public class GeneratorTest {
            public static void main(String[] args) {
                Config config = new Config();
                //使用lombok注解
                config.setUseLombok(true);
                //生成日志路径
                config.setGenLogFile(Paths.get(System.getProperty("user.home"), "gen.log").toString());
                //数据库连接信息，不反向生成数据库表则无需配置
                config.setUrl("jdbc:mysql://localhost:3306/v-orm-demo?useUnicode=true");
                config.setUsername("root");
                config.setPassword("root");
                //实体类的包路径，该包下所有符合条件的实体类都将自动生成代码
                config.setEntityPackage("io.github.itliwei.vboot.vorm.entity");
                //(可选项) 具体实体类的名称，如果配置了，则只生成指定类的数据
                config.setEntityName(TestEntity.class.getName());
                //生成查询类的包路径
                config.setQueryModelPackage("io.github.itliwei.vboot.vorm.query");
                //生成VO类的包路径
                config.setVoPackage("io.github.itliwei.vboot.vorm.vo");
                //生成Service类的包路径
                config.setServicePackage("io.github.itliwei.vboot.vorm.service");
                //生成Controller类的包路径
                config.setControllerPackage("io.github.itliwei.vboot.vorm.controller");
                //生成vue页面的路径
                config.setElementPackage("/Users/vince/myproject/v-boot/vue");
                config.setElementPath("/Users/vince/myproject/v-boot/vue");
        
                //具体生成调用方法
                Generator.generate(config
                        , new VoHandler()//生成VO对象
                        , new QueryModelHandler()//生成查询类
                        , new ServiceHandler()//生成Service类
                        , new ControllerHandler()//生成Controller类
                        , new ElementHandler()//生成Element文件
                       , new MysqlHandler(true)//生成数据库表
                );
        
            }
        }
        
     编写好后执行main方法，即可生成对应代码。

4.  代码展示
    
    controller：提供基础的增、删、改、查
    
        @Slf4j
        @RestController
        @RequestMapping("api/user")
        @Api(tags = "用户接口",description = "用户接口")
        public class TestController {
        
            @Autowired
            private TestService testService;
        
        
            @GetMapping("/info/{id}")
            @ApiOperation(value = "根据ID查找",httpMethod = "GET")
            public Resp<TestVo> getById(@PathVariable long id) {
                TestEntity result = testService.findById(id);
                if (result != null){
                    TestVo testEntityVo =  TestVo.convert2TestVo(result);
                    return Resp.success(testEntityVo);
                }
                return Resp.error(ErrorCode.DATA_NOT_EXIST,"id:"+id);
            }
        
        
            @PostMapping("/page/query")
            @ApiOperation(value = "分页查找内容",httpMethod = "POST")
            public Resp<Page<TestVo>> pageQuery(@RequestBody TestQueryModel queryModel) {
                Page<TestEntity> result = testService.findPage(queryModel);
                Page<TestVo> voPage = PageBuilder.copyAndConvert(result, TestVo::convert2TestVo);
                return Resp.success(voPage);
            }
        
            @PostMapping("/save")
            @ApiOperation(value = "保存",httpMethod = "POST")
            public Resp<TestVo> save(@Valid @RequestBody TestDto testEntityDto) {
                TestEntity entity = testEntityDto.convert2TestEntity();
                int result = testService.save(entity);
                if (result > 0){
                    TestVo testEntityVo =  TestVo.convert2TestVo(entity);
                    return Resp.success(testEntityVo);
                }
                return Resp.error(ErrorCode.SERVER,"保存数据失败");
            }
        
            @PostMapping("/update")
            @ApiOperation(value = "修改",httpMethod = "POST")
            public Resp update(@Valid @RequestBody TestDto testEntityDto) {
                TestEntity entity = testEntityDto.convert2TestEntity();
                int result = testService.update(entity);
                if (result > 0) {
                    return Resp.success();
                }
                return Resp.error(ErrorCode.SERVER,"修改数据失败");
            }
        
            @GetMapping("/delete/{id}")
            @ApiOperation(value = "根据ID删除",httpMethod = "GET")
            public Resp delete(@PathVariable long id) {
                int result = testService.delete(id);
                if (result > 0) {
                    return Resp.success();
                }
                return Resp.error(ErrorCode.SERVER,"删除数据失败");
            }
        
        
        }
    
    service：很简洁，继承BaseService方法即可。
    
        @Service
        @Slf4j
        public class TestService extends BaseService<TestEntity> {
        
        }
    
    VO、DTO：类似基本的属性都已经生成好，还提供了基础的类型准换方法。
    
        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @ApiModel(value = "TestDto", description = "TestDto")
        public class TestDto implements Serializable {
            @ApiModelProperty(value = "id")
            private Long id;
            @ApiModelProperty(value = "姓名")
            private String username;
            /* 扩展 */
        
            /* 转换 */
            public TestEntity convert2TestEntity(){
                TestDtoConvert convert = new TestDtoConvert();
                TestEntity target = convert.convert(this);
                return target;
            }
        
        
            private static class TestDtoConvert extends Converter<TestDto, TestEntity> {
                @Override
                protected TestEntity doForward(TestDto source) {
                    TestEntity target = new TestEntity();
                    BeanConvertUtil.convert(target,source);
                    return target;
                }
        
                @Override
                protected TestDto doBackward(TestEntity source) {
                    throw new AssertionError("不支持逆向转化方法!");
                }
            }
        
        }
        
     QueryModel：设置的查询方法都已经自动生成
     
         @Getter
         @Setter
         @Builder
         @NoArgsConstructor
         @AllArgsConstructor
         @ApiModel(value = "TestQueryModel", description = "TestQueryModel")
         public class TestQueryModel  extends QueryModel implements Serializable {
            private Long idEQ;
            @ApiModelProperty(value ="姓名")
            private String usernameEQ;
            @ApiModelProperty(value ="姓名")
            private List<String> usernameIN;
            @ApiModelProperty(value ="jobId",example="0")
            private Long jobIdEQ;
            @ApiModelProperty(value ="jobId",example="0")
            private List<Long> jobIdIN;
            @ApiModelProperty(value ="年龄",example="0")
            private Integer ageEQ;
            @ApiModelProperty(value ="年龄",example="0")
            private List<Integer> ageIN;
            @ApiModelProperty(value ="地址")
            private String addressEQ;
            @ApiModelProperty(value ="地址")
            private List<String> addressIN;
         }
    
5.  项目启动

    需要在SpringBoot启动类上加上：``io.github.itliwei.vboot.vorm.orm``因为这里存在基础的bean的自动适配。
    

    @SpringBootApplication(scanBasePackages = {"io.github.itliwei.vboot.vorm.orm","io.github.itliwei.vboot.vorm"})
    public class VOrmSpringBootDemoApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(VOrmSpringBootDemoApplication.class, args);
        }
    
    }

6.  项目配置：和普通的mybatis配置没有任何不同


    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/v-orm-demo?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
    spring.datasource.username=root
    spring.datasource.password=root
    
    #如果自动开启swagger的话，路径写到自己的项目名
    swagger.docket.base-package=io.github.itliwei.vboot

    

7.  启动效果掩饰
    
    查看swagger接口文档：ip:port/doc.html
    
    
#### 框架说明


