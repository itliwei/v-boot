package io.github.itliwei.vboot.vorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"io.github.itliwei.vboot.vorm.orm","io.github.itliwei.vboot.vorm"})
public class VOrmSpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VOrmSpringBootDemoApplication.class, args);
    }

}
