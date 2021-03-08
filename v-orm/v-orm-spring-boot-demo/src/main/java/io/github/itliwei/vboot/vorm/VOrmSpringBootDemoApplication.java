package io.github.itliwei.vboot.vorm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"io.github.itliwei.vboot.vorm.orm","io.github.itliwei.vboot.vorm"})
@MapperScan("io.github.itliwei.vboot.vorm.mapper")
public class VOrmSpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VOrmSpringBootDemoApplication.class, args);
    }

}
