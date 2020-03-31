package com.debug.eureka.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Eureka服务状态事件监测
 * @author Lyb
 * @since 2019-10-11 13:46
 */
@Slf4j
@Component
public class EurekaStateMonitor {
    /**
     * 服务注册后触发
     * @param event 服务注册事件
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        log.info("服务{}注册...", event.getInstanceInfo().getAppName());
    }

    /**
     * 服务离线后触发
     * @param event 服务下线事件
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        log.info("服务{}-{}下线...", event.getServerId(), event.getAppName());
    }

    /**
     * 服务进行心跳时检测成功后触发
     * @param event 服务续约事件
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        log.info("服务{}-{}续约...", event.getServerId(), event.getAppName());
    }

    /**
     * 注册中心即该Eureka应用，会先启动
     * @param event 注册中心启动事件
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.info("注册中心启动...");
    }

    /**
     * Eureka服务，会后于注册中心启动
     * @param event EurekaServer启动事件
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.info("EurekaServer启动...");
    }
}
