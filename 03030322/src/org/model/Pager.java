package org.model;

public class Pager {
	private int pageNow;
	private int pageSize=4;
	private int totalPage;
	private int totalSize;
	private boolean hasFirst;
	private boolean hasPre;
	private boolean hasNext;
	private boolean hasLast;
	public Pager(int pageNow,int totalSize){
		//利用构造方法为变量赋值
		this.pageNow=pageNow;
		this.totalSize=totalSize;
	}
	
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalPage() {
		//一共多少页的算法
		totalPage=getTotalSize()/getPageSize();
		if(totalSize%pageSize!=0)  totalPage++;
		
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	public boolean isHasFirst() {
		//如果当前为第一页就没有首页
		if(pageNow==1)  return false;
		else  return true;
	}
	
	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}
	public boolean isHasPre() {
		//如果有首页就有前一页，因为有首页就不是第一页
		if(this.isHasFirst())  return true;
		else  return false;
	}
	
	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	
	public boolean isHasNext() {
		//如果有尾页就有下一页，因为有尾页表明不是最后一页
		if(isHasLast())  return true;
		else  return false;
	}
	
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	private boolean isHasLast() {
		// TODO Auto-generated method stub
		//如果不是最有一页就有尾页
		if(pageNow==this.getTotalPage())  return false;
		else  return true;
	}
	
	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}
}
