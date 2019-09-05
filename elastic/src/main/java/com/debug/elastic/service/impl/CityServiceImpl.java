package com.debug.elastic.service.impl;

import com.debug.elastic.entity.City;
import com.debug.elastic.repository.CityRepository;
import com.debug.elastic.service.CityService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lyb
 * @since 2019-05-31
 */
@Service
public class CityServiceImpl implements CityService {
    private static final Integer PAGE_SIZE = 12;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City searchCity(Long id) {
        return cityRepository.findById(id).get();
    }
}
