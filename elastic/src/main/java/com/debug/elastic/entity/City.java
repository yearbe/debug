package com.debug.elastic.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author Lyb
 * @since 2019-05-31
 */
@Data
@Document(indexName = "province", type = "city")
public class City implements Serializable {
    private Long id;
    private String name;
    private String desc;
}
