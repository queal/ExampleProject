package com.example.ibatis.dao;

import com.example.ibatis.modle.Person;

public interface PersonMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Mon Jun 27 16:35:32 CST 2016
     */
    int deleteByPrimaryKey(Integer autoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Mon Jun 27 16:35:32 CST 2016
     */
    int insert(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Mon Jun 27 16:35:32 CST 2016
     */
    int insertSelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Mon Jun 27 16:35:32 CST 2016
     */
    Person selectByPrimaryKey(Integer autoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Mon Jun 27 16:35:32 CST 2016
     */
    int updateByPrimaryKeySelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Mon Jun 27 16:35:32 CST 2016
     */
    int updateByPrimaryKey(Person record);
}