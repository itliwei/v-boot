package io.github.itliwei.vboot.vorm;




import io.github.itliwei.vboot.vorm.entity.TestEntity;
import io.github.itliwei.vboot.vorm.generator.Config;
import io.github.itliwei.vboot.vorm.generator.Generator;
import io.github.itliwei.vboot.vorm.generator.handler.*;

import java.nio.file.Paths;

/**
 * @author : liwei
 * @date : 2019/05/21 21:50
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
//                , new ElementHandler()//生成Element文件
               , new MysqlHandler(true)//生成数据库表
        );

    }
}
