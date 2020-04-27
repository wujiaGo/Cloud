package cn.wujia.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Value("${server.port}")
    private String port;
    private RestTemplate restTemplate;

    @GetMapping("/holle")
    public String holle() {
        return "holle";
    }

    @GetMapping("/Ribbon/test")
    public String ribbonTest() {
        return "端口号" + port;
    }

    @GetMapping("/article/callhello")
    public String callHello() {
        return restTemplate.getForObject("http://localhost:8761/eureka/", String.class);
    }
}
