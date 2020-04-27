package cn.wujia.ribboncloud;

import cn.wujia.myRibbon.MyRibbon;
import cn.wujia.myRibbon.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "EUREKA-CLIENT-USER-SERVICE",configuration = MyRule.class)
public class RibbonCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonCloudApplication.class, args);
    }

}
