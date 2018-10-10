package com.lance.code.download.service;

public class Book {
	//书的主页
	String href;
	//书名
	String bookname;
	//书ID
	Integer bookid;
	//最新更新章节
	Integer updateNum;
	//上次更新章节
	Integer lastNum;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public Integer getUpdateNum() {
		return updateNum;
	}
	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
	}
	public Integer getLastNum() {
		return lastNum;
	}
	public void setLastNum(Integer lastNum) {
		this.lastNum = lastNum;
	}

	
	
}