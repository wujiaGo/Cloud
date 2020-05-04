package cn.wujia.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
