package com.debug.mybatis.mapper;

import com.debug.mybatis.entity.FctDeclaration;

public interface FctDeclarationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_DECLARATION
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long autoid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_DECLARATION
     *
     * @mbggenerated
     */
    int insert(FctDeclaration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_DECLARATION
     *
     * @mbggenerated
     */
    int insertSelective(FctDeclaration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_DECLARATION
     *
     * @mbggenerated
     */
    FctDeclaration selectByPrimaryKey(Long autoid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_DECLARATION
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FctDeclaration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FCT_DECLARATION
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FctDeclaration record);
}