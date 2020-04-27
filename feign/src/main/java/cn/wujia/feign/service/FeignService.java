package cn.wujia.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "EUREKA-CLIENT-USER-SERVICE") //开启Feign
public interface FeignService {
    @GetMapping("/Ribbon/test")
    public String ribbonTest();
}
