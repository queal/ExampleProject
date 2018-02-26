package com.example.pojo;

public class QueryCondition {

	public static final int DEFAULT_PAGE_SIZE = 20;
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	private Integer currPage = 1;
	private Integer startIndex;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

}
