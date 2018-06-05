package com.example.service.person.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.constants.Constant;
import com.example.ibatis.dao.PersonMapper;
import com.example.ibatis.model.Person;
import com.example.pojo.PageList;
import com.example.pojo.QueryCondition;
import com.example.service.person.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper personMapper;

	@Override
	public String updateMoney(Integer autoId, String useMoney) {
		BigDecimal balanceMoney = personMapper.selectMoney(autoId);
		BigDecimal usedMoney = new BigDecimal(useMoney);

		if (Constant.ZERO_BIGDECIMAL.compareTo(balanceMoney) < 0) {
			balanceMoney = balanceMoney.subtract(usedMoney);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("autoId", autoId);
			params.put("money", useMoney);
			personMapper.updateMoney(params);
		}
		
		return balanceMoney.toString();
	}

	@Override
	public PageList<Person> queryPersonList(QueryCondition condition) {
		PageList<Person> pageList = new PageList<Person>();
		pageList.setCondition(condition);

		int totleCount = personMapper.queryPersonListCount(condition);
		if (totleCount > 0) {
			List<Person> dataList = personMapper.queryPersonList(condition);
			pageList.setDataList(dataList);
			pageList.setTotleCount(totleCount);
		}

		return pageList;
	}

	@Override
	public int insertExist(Person record) {
		Person p = new Person();
		p.setAutoId(16);
		p.setName(record.getName() + "_" + RandomStringUtils.randomNumeric(3));
		personMapper.updateByPrimaryKeySelective(p);

		int ret = personMapper.insertExist(record);
		if (ret > 0) {
			System.out.println("插入成功");
		} else {
			System.out.println("插入失败");
			Integer.parseInt("s");
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		return ret;
	}

	@Override
	public Person selectByPrimaryKey(Integer autoId) {
		return personMapper.selectByPrimaryKey(autoId);
	}

}
