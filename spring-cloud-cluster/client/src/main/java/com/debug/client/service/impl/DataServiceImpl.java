package com.debug.client.service.impl;

import com.debug.client.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lyb
 * @since 2019-09-29 15:50
 */
@Service
public class DataServiceImpl implements DataService {

    private final RestTemplate restTemplate;

    @Autowired
    public DataServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String query(String name) {
        return restTemplate.getForObject("http://service-provider-cluster/api?param=" + name, String.class);
    }
}
