package cn.wujia.controller;

import cn.wujia.pool.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Value("${name}")
    private String name;

    @GetMapping("/name")
    public String name() {
        return name;
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/article/callhello")
    public String callHello() {
        return restTemplate.getForObject("http://eureka-client-user-service/holle", String.class);
    }

    @GetMapping("/call/data")
    public String data(@RequestParam String name) {
        return restTemplate.getForObject
                ("http://localhost:8084/house/data?name=" + name, String.class);
    }

    @GetMapping("/call/data/{name}")
    public String name(@PathVariable String name) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:8084/house/data/{name}", String.class, name);
        if (responseEntity.getStatusCodeValue() == 200) {
            return responseEntity.getBody();
        }
        return null;
    }

    @GetMapping("/call/post")
    public String post() {
        User user = new User();
        user.setName("wujia");
        return restTemplate.postForObject("http://localhost:8084/house/data/post",
                user, String.class);
    }

    @GetMapping("/sleuth/test")
    public String sleuthTest() {
        return "yes";
    }
}