package cn.wujia.feign.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfigureaion {
    @Bean
    public Request.Options options() {//超时控制
        // 1指的是建立连接所用的时间，适用于网络状况正常的情况下,两端还接所用的时间
        // 2指的是建应连接后从服务器读取到可用资源所用的时间
        return new Request.Options(5000, 5000);
    }

    //设置日志级别
    @Bean
    public Logger.Level fLevel() {
        return Logger.Level.FULL;
    }
}
