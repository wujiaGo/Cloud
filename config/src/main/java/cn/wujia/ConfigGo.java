package cn.wujia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigGo {
     public static void main(String[] args) {
        SpringApplication.run(ConfigGo.class,args);
    }
}
