package com.debug.client.service;

import com.debug.client.service.impl.PhoneServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lyb
 * @since 2020-06-03
 */
@FeignClient(value = "service-provider-cluster", fallback = PhoneServiceImpl.class)
public interface PhoneService {
    /**
     * feign demo
     * @param phone
     * @return
     */
    @RequestMapping("/api")
    String search(@RequestParam("param") String phone);
}
