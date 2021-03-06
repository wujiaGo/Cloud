package cn.wujia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient 这是个开启服务提供注解
//开启sleuth
@EnableDiscoveryClient
public class TestGo {
    public static void main(String[] args) {
        SpringApplication.run(TestGo.class, args);
    }
}
