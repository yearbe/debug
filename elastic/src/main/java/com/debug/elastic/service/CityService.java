package com.debug.elastic.service;

import com.debug.elastic.entity.City;

/**
 * @author Lyb
 * @since 2019-05-31
 */
public interface CityService {

    Long saveCity(City city);

    void deleteAll();

    void delete(City city);

    void deleteById(Long id);

    City searchCity(Long id);
}
