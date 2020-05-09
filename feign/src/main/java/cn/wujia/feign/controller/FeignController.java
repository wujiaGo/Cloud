package cn.wujia.feign.controller;

import cn.wujia.feign.service.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignController {
    @Resource
    private FeignService feignService;

    @GetMapping("/test")
    public String getDate() {
        return feignService.ribbonTest();
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout() {
        //openfeign-ribbon，客户端一般默认等待1秒钟
        return feignService.paymentFeignTimeout();
    }
}
