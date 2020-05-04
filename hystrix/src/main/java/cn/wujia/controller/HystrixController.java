package cn.wujia.controller;

import cn.wujia.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @GetMapping("/PaymentInfo/{id}")
    public String PaymentInfo(@PathVariable("id") Integer id) {
        return paymentService.PaymentInfo(id);
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") Integer id) {
        return paymentService.test(id);
    }
}
