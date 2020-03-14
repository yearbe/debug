package com.debug.service.controller;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lyb
 * @since 2019-10-08 10:18
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private EurekaClient eurekaClient;

    /*@Autowired
    private DiscoveryClient discoveryClient;*/

    @RequestMapping("/instance")
    public Object getInstance() {
        // 返回应用信息
        return eurekaClient.getInstancesByVipAddress("service-provider-cluster", false);
    }

    /*@RequestMapping("/discovery")
    public Object discovery() {
        return discoveryClient.getInstancesByVipAddress("service-provider-cluster", false);
    }*/
}
