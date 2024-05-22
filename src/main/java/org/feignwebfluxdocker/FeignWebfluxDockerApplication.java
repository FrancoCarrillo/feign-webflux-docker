package org.feignwebfluxdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignWebfluxDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignWebfluxDockerApplication.class, args);
    }

}
