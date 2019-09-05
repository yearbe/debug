package com.debug.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Lyb
 * @since 2019-03-20
 */
@Mapper
@Repository
public interface OperateDatabaseMapper {

    /**
     * 创建数据库
     * @param database
     */
    void createDatabase(@Param("database") String database);

    /**
     * 删除数据库
     * @param database
     */
    void dropDatabase(@Param("database")String database);
}
