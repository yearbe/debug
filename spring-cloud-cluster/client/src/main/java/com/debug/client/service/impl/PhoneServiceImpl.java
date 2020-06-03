package com.debug.client.service.impl;

import com.debug.client.service.PhoneService;
import org.springframework.stereotype.Service;

/**
 * @author Lyb
 * @since 2020-06-03
 */
@Service
public class PhoneServiceImpl implements PhoneService {
    @Override
    public String search(String phone) {
        return "Error: search [" + phone + "] failed.";
    }
}
