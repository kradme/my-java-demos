package cn.lang.sleuth.demo;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class SleuthServer {

    @RequestMapping("/api")
    public String printDate() {
        return new Date().toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(SleuthServer.class,
                "--spring.application.name=backend",
                "--server.port=9000"
        );
    }
}