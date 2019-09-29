package com.debug.client.controller;

import com.debug.client.service.DataService;
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

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/query")
    public String query(String name) {
        return dataService.query(name);
    }
}
