package com.debug.mybatis.mapper;

import com.debug.mybatis.entity.FctWorkguidedetail;

public interface FctWorkguidedetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDEDETAIL
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long autoid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDEDETAIL
     *
     * @mbggenerated
     */
    int insert(FctWorkguidedetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDEDETAIL
     *
     * @mbggenerated
     */
    int insertSelective(FctWorkguidedetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDEDETAIL
     *
     * @mbggenerated
     */
    FctWorkguidedetail selectByPrimaryKey(Long autoid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDEDETAIL
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FctWorkguidedetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_WORKGUIDEDETAIL
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FctWorkguidedetail record);
}