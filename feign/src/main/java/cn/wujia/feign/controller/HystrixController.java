package cn.wujia.feign.controller;

import cn.wujia.feign.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//全局
@DefaultProperties(defaultFallback = "fobake")
public class HystrixController {
    @Autowired
    HystrixService hystrixService;

    @GetMapping("/PaymentInfo/{id}")
    public String PaymentInfo(@PathVariable("id") Integer id) {
        return hystrixService.PaymentInfo(id);
    }

    @GetMapping("/test/{id}")
//    @HystrixCommand(fallbackMethod = "fobake",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    }) 指定
    @HystrixCommand//声明是要降级的
    public String test(@PathVariable("id") Integer id) {
        return hystrixService.test(id);
    }

    public String fobake(@PathVariable("id") Integer id){
        return "80端口:forbake";
    }

}
