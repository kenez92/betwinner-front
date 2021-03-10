package com.kenez92;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class BetwinnerFrontApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(BetwinnerFrontApplication.class, args);
//    }
//
//}

@SpringBootApplication
public class BetwinnerFrontApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BetwinnerFrontApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BetwinnerFrontApplication.class);
    }
}
