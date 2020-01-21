package com.bingo.showme.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Lyb
 * @since 2020-01-10
 */
@Slf4j
//@Component
public class ConfigHolder {
    private Properties properties;

    public Properties getConfig() {
        return properties;
    }

    private Resource getResource(String filename) throws MalformedURLException {
        Resource resource = new UrlResource("file:./" + filename);
        if (!resource.exists()) {
            resource = new ClassPathResource(filename);
        }
        return resource;
    }

    @PostConstruct
    private void init() {
        try {
            String filename = "config.properties";
            Resource resource = getResource(filename);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Paths.get(resource.getFile().getParent()).register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
            properties = PropertiesLoaderUtils.loadProperties(resource);
            log.info("初始化config.properties配置完成");
            log.info("配置项：{}", getConfig().get("test.flag"));
            Thread watchThread = new Thread(() -> {
                while (true) {
                    try {
                        WatchKey watchKey = watchService.take();
                        for (WatchEvent<?> event : watchKey.pollEvents()) {
                            if (Objects.equals(event.context().toString(), filename)) {
                                properties = PropertiesLoaderUtils.loadProperties(resource);
                                log.info("更新config.properties配置完成");
                                log.info("配置项：{}", getConfig().get("test.flag"));
                                break;
                            }
                        }
                        watchKey.reset();
                    } catch (Exception e) {
                        log.error("监控配置文件失败", e);
                    }
                }
            });
            watchThread.setDaemon(true);
            watchThread.start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    watchService.close();
                } catch (IOException e) {
                    log.error("监控服务关闭失败", e);
                }
            }));
        } catch (Exception e) {
            log.error("读取配置文件失败", e);
        }
    }
}
