package fan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 系统启动类
 *
 * @author Fan
 * @since 2023/7/6 16:18
 */
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class FanCaptchaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanCaptchaApplication.class, args);
    }

}
