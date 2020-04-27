package cn.wujia.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    //@LoadBalanced 向注册中心获取数据 用服务提供者的name就加这个
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
