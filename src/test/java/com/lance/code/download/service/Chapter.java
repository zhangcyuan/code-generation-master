package com.lance.code.download.service;

public class Chapter {
	//章节链接
	String href;
	//章节标题
	String title;
	//章节ID
	String chapter_id;
	//第N话
	Integer sort;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(String chapter_id) {
		this.chapter_id = chapter_id;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "Chapter [href=" + href + ", title=" + title + ", chapter_id=" + chapter_id + ", sort=" + sort + "]";
	}
	
	
}
