package com.lance.code.download.service;

import java.util.List;

public class Book {
	//书的主页
	String href;
	//书名
	String bookname;
	//书ID
	Integer bookid;
	//最新更新章节
	Integer updateNum;
	//章节列表
	List<Chapter> chapters;
	
	
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
	
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	@Override
	public String toString() {
		return "Book [href=" + href + ", bookname=" + bookname + ", bookid=" + bookid + ", updateNum=" + updateNum
				+ ", chapters=" + chapters + "]";
	}


	
	
}