package com.debug.mybatis.mapper;

import com.debug.mybatis.entity.FctWorkguide;

public interface FctWorkguideMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long autoid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDE
     *
     * @mbggenerated
     */
    int insert(FctWorkguide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDE
     *
     * @mbggenerated
     */
    int insertSelective(FctWorkguide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDE
     *
     * @mbggenerated
     */
    FctWorkguide selectByPrimaryKey(Long autoid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FctWorkguide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FctWorkguide record);
}