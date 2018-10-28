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
	//更新完结状态  完结 或 连载中
	String updateState;
	//书目录  id_bookname
	String bookpath;
	//阅读数
	String readCount;
	//标签 分类
	List<String> bookTag;
	//下载章节数
	Integer downloadNum;
	//属于第几页
	Integer pageNum;
	
	
	//章节列表
	//List<Chapter> chapters;
	
	
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
	public Integer getDownloadNum() {
		return downloadNum;
	}
	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}
	public String getReadCount() {
		return readCount;
	}
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	public String getUpdateState() {
		return updateState;
	}
	public void setUpdateState(String updateState) {
		this.updateState = updateState;
	}
	public String getBookpath() {
		return bookpath;
	}
	public void setBookpath(String bookpath) {
		this.bookpath = bookpath;
	}
	public List<String> getBookTag() {
		return bookTag;
	}
	public void setBookTag(List<String> bookTag) {
		this.bookTag = bookTag;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	@Override
	public String toString() {
		return "Book [href=" + href + ", bookname=" + bookname + ", bookid=" + bookid + ", updateNum=" + updateNum
				+ ", updateState=" + updateState + ", bookpath=" + bookpath + ", readCount=" + readCount + ", bookTag="
				+ bookTag + "]";
	}

	
	
}