package cn.wujia.ribboncloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DataController {
    @Autowired
    RestTemplate restTemplate;
    private String localhost = "http://EUREKA-CLIENT-USER-SERVICE";
    @GetMapping("/call/data")
    public String getDate() {
        return restTemplate.getForObject(localhost+"/Ribbon/test", String.class);
    }


}
