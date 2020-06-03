package com.debug.client.controller;

import com.debug.client.service.DataService;
import com.debug.client.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lyb
 * @since 2019-09-29 15:50
 */
@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;
    private final PhoneService phoneService;

    @Autowired
    public DataController(DataService dataService, PhoneService phoneService) {
        this.dataService = dataService;
        this.phoneService = phoneService;
    }

    @RequestMapping("/query")
    public String query(String name) {
        return dataService.query(name);
    }

    @RequestMapping("/search")
    public String search(String phone) {
        return phoneService.search(phone);
    }
}
