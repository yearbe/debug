package com.debug.elastic;

import com.debug.elastic.entity.City;
import com.debug.elastic.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticApplicationTests {

    @Autowired
    private CityService cityService;

    @Test
    public void contextLoads() {
        /*City city = new City();
        city.setId(1L);
        city.setName("江门");
        city.setDesc("广东");
        cityService.saveCity(city);*/

        /*City city = cityService.searchCity(1L);
        System.out.println(city);*/

        cityService.deleteAll();
    }

}
