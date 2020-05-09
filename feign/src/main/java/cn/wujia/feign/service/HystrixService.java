package cn.wujia.feign.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "EUREKA-HYSTRIX-SERVICE",
        //单个方法处理
         fallback = HystrixServiceImpl.class)
public interface HystrixService {
    @GetMapping("/PaymentInfo/{id}")
    public String PaymentInfo(@PathVariable("id") Integer id);

    @GetMapping("/test/{id}")

    public String test(@PathVariable("id") Integer id);
}
