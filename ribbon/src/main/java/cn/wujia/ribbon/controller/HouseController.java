package cn.wujia.ribbon.controller;

import cn.wujia.ribbon.pool.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HouseController {
    @GetMapping("/house/data")
    public String data(@RequestParam("name") String name) {
        return name;
    }

    @GetMapping("/house/data/{name}")
    public String name(@PathVariable String name) {
        return name;
    }

    @PostMapping("/house/data/post")
    public String post(@RequestBody User user) {
        return user.getName();
    }
}
