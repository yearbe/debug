package com.debug.service.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @author Lyb
 * @since 2019-10-08 10:34
 */
@Slf4j
// 打开注解后，会在应用启动后心跳检测第二次时将应用状态修改为DOWN
//@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    private int count = 0;
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (count > 1) {
            builder.down().withDetail("status", false);
            log.info("应用下线...");
        } else {
            builder.up().withDetail("status", true);
            count++;
            log.info("应用检测正常...");
        }
    }
}
