package com.example.pojo;

import java.util.List;

public class PageList<E> {
	private Integer totleCount;
	private Integer totlePageNum;
	private QueryCondition condition;
	private List<E> dataList;

	public Integer getTotleCount() {
		return totleCount;
	}

	public void setTotleCount(Integer totleCount) {
		this.totleCount = totleCount;
		float nrOfPages = ((float) totleCount) / condition.getPageSize();
		setTotlePageNum((int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
	}

	public Integer getTotlePageNum() {
		return totlePageNum;
	}

	public void setTotlePageNum(Integer totlePageNum) {
		this.totlePageNum = totlePageNum;
	}

	public QueryCondition getCondition() {
		return condition;
	}

	public void setCondition(QueryCondition condition) {
		condition.setStartIndex((condition.getCurrPage() - 1) * condition.getPageSize());
		this.condition = condition;
	}

	public List<E> getDataList() {
		return dataList;
	}

	public void setDataList(List<E> dataList) {
		this.dataList = dataList;
	}

}
