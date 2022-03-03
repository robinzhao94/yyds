package icu.oo7.yyds.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminRbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminRbacApplication.class, args);
    }

}
