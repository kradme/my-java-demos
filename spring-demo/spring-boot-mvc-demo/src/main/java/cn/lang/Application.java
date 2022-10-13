package cn.lang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.lang")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
