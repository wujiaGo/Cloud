package cn.wujia.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String PaymentInfo(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "id:" + id.toString() + "ok";
    }

    @HystrixCommand(fallbackMethod = "fobake",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String test(Integer id) {
        int tume = 5;
        //int i = 1 / 0;
        try {
            TimeUnit.SECONDS.sleep(tume);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + "id:" + id + "ok,耗时:" + tume;
    }

    public String fobake(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "id:" + id + "失败";
    }
    //====服务熔断
    @HystrixCommand(fallbackMethod = "Fobake" ,commandProperties = {
            @HystrixProperty(name = "circuitBreaker. enabled" ,value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker. requestVolumeThreshold" ,value = "10"),// 请求次数
            @HystrixProperty(name =" circuitBreaker.sleepWindowInMilliseconds" ,value ="10000"), //时间窗口期
            @HystrixProperty(name =" circuitBreaker. errorThresholdPercentage" ,value ="60"),// 失败率达到多少后跳闸

    })
    public String rongDuan(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("id 不能为空");
        }
        String number = IdUtil.simpleUUID();
        return "线程池" + Thread.currentThread().getName() + "id:" + number + "ok";
    }
    public String Fobake(@PathVariable("id") Integer id){
        return "线程池" + Thread.currentThread().getName() + "id:" + id + "fobake";
    }

}
 