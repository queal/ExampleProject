package com.example.service.person;

import com.example.ibatis.model.Person;
import com.example.pojo.PageList;
import com.example.pojo.QueryCondition;

/**
 * Person实体基本操作服务类
 * 
 * @author jingye.pan
 *
 */
public interface PersonService {

	/**
	 * 根据条件获取Person实体列表
	 * 
	 * @param condition
	 *            分页查询条件
	 * 
	 * @return Person实体列表
	 */
	public PageList<Person> queryPersonList(QueryCondition condition);

	/**
	 * 根据autoId更新用户余额
	 * 
	 * @param autoId
	 *            用户主键
	 * 
	 * @param useMoney
	 *            使用余额 业务逻辑：用户余额 = 用户剩余金额 - 使用余额
	 * 
	 * @return 用户剩余金额
	 */
	public String updateMoney(Integer autoId, String useMoney);

	public int insertExist(Person record);

	Person selectByPrimaryKey(Integer autoId);

}
