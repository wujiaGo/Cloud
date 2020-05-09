package cn.wujia.feign.service;

import org.springframework.stereotype.Component;

@Component
public class HystrixServiceImpl implements HystrixService{
    @Override
    public String PaymentInfo(Integer id) {
        return "PaymentInfo --失败";
    }

    @Override
    public String test(Integer id) {
        return "test --失败";
    }
}
