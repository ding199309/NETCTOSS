package com.tarena.entity.page;

public abstract class Page {
	/*
	 * 默认当前页面为1
	 */
	private int currentPage = 1;
	/*
	 * 默认显示5条
	 */
	private int pageSize = 5;
	
	private int begin;
	private int end;
	
	private int totalPage;
	private int rows;
	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRows() {
		
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getBegin() {

		begin = (currentPage - 1) * pageSize;
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		end = currentPage * pageSize ;
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		if(rows%pageSize==0){
			totalPage=rows/pageSize;
			
		}else{
			totalPage=rows/pageSize+1;
		}
			
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	

}
