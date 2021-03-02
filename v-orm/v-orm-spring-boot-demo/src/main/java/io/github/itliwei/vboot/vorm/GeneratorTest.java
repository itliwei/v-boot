package io.github.itliwei.vboot.vorm;




import io.github.itliwei.vboot.vorm.entity.TestEntity;
import io.github.itliwei.vboot.vorm.generator.Config;
import io.github.itliwei.vboot.vorm.generator.Generator;
import io.github.itliwei.vboot.vorm.generator.handler.*;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author : liwei
 * @date : 2019/05/21 21:50
 */
public class GeneratorTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.setGenLogFile(Paths.get(System.getProperty("user.home"), "gen.log").toString());
        config.setUrl("jdbc:mysql://106.13.146.82:3306/v-orm-demo?useUnicode=true");
        config.setEntityPackage("io.github.itliwei.vboot.vorm.entity");
        config.setEntityName(TestEntity.class.getName());
        config.setQueryModelPackage("io.github.itliwei.vboot.vorm.query");
        config.setVoPackage("io.github.itliwei.vboot.vorm.vo");
        config.setServicePackage("io.github.itliwei.vboot.vorm.service");
        config.setControllerPackage("io.github.itliwei.vboot.vorm.controller");

        config.setUsername("root");
        config.setPassword("root");
        config.setUseLombok(true);

        config.setElementPackage("/Users/vince/myproject/mvcorm/vue");
        config.setElementPath("/Users/vince/myproject/mvcorm/vue");

        Generator.generate(config
                , new VoHandler()
                , new QueryModelHandler()
                , new ServiceHandler()
                , new ControllerHandler()
//                , new ElementHandler()
               , new MysqlHandler(true)
        );

    }
}
