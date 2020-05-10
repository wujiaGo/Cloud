package cn.wujai.sleuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class SleuthController {
    @Autowired
    RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/sleuth/test")
    public String test() {
        System.out.println("进入-sleuth");
        String str = restTemplate.getForObject("http://localhost:8082/sleuth/test", String.class);
        return str;
    }
}
