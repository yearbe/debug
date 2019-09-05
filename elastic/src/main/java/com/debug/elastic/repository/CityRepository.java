package com.debug.elastic.repository;

import com.debug.elastic.entity.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lyb
 * @since 2019-05-31
 */
@Repository
public interface CityRepository extends ElasticsearchRepository<City, Long> {
}
