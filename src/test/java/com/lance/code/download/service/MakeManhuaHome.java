package com.lance.code.download.service;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lance.code.generation.utils.TxtUtil;

public class MakeManhuaHome {
	public static void main(String[] args) {
		//1.读取readme.txt文件
		String lastbooks = TxtUtil.readTxt(ManhuaTest.filePath+"readme.txt");
		Gson gson = new Gson();	
		Type mtype = new TypeToken<Map<Integer, Book>>() {
        }.getType();
	    Map<Integer,Book> bookMap = gson.fromJson(lastbooks,mtype);
		//2.生成每一本书的信息
	    //3.组装到html文件内容中
		StringBuffer sb = new StringBuffer();
		sb.append(htmlHeader("主页"));
		sb.append("<div class='category-list-div-ul '><div class='w-shelfBooklist classifly-hot'><ul>");
		for (Integer bookId : bookMap.keySet()) {
			Book book = bookMap.get(bookId);
			sb.append("<li class='w-shelfBookinfo'>");
			sb.append("<a href='/book/cartoon/info?book_id=43'>");
			sb.append("<div class='w-bookPic'>");
			sb.append("<img src='./").append(book.getBookid()+"_"+book.getBookname()+"/"+book.getBookname()).append(".jpg'>");
			sb.append("<div class='classifly-chapter'>更至第").append(book.getUpdateNum()).append("话</div>");
			sb.append("</div><div class='w-bookName'>").append(book.getBookname()).append("</div></a></li>");
			
			//生成每本书的章节目录
			makeMulu(book);
			
			
			
			//生成每个章节的漫画内容
			break;
		}
		sb.append("</ul></div></div></body></html>");
		
		
		//System.out.println(sb);
		
		//4.保存html文件到主目录  主页
		TxtUtil.writeTxt(ManhuaTest.filePath+"index.html", sb.toString());
		
		
		
		
	}
	
	public static void makeMulu(Book book){
		StringBuffer sb = new StringBuffer();
		sb.append(htmlHeader(book.getBookname()+"-目录"));
		sb.append("<ul id='sort-list' class='book-chapter-list'>");
		
		//读取书下所有目录取名称
		File file=new File(ManhuaTest.filePath+book.getBookid()+"_"+book.getBookname()+"/");
		File[] files =	file.listFiles();
		for (File file2 : files) {
			if(file2.isDirectory()){
				String chapterName =file2.getName().split("_")[1];
				String chapterId = file2.getName().split("_")[0];
				sb.append("<li onclick='window.location.href = '/book/cartoon/read?book_id=43&chapter_id=1993''>");
				sb.append("<div class='orderid' data-chapter='1'></div>");
				sb.append("<p class='overhidden' onclick='window.location.href = '/book/cartoon/read?book_id=43chapter_id=1993''>").append(chapterName).append("</p></li>");
				System.out.println();
				
			}
		}
		
		sb.append("</ul></body></html>");
		
	}
	
	
	
	
	
	
	public static String htmlHeader(String title){
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html><html><head><meta charset='utf-8'><title>").append(title).append("</title>"
				+ "<link rel='stylesheet' href='http://x13043.sanby.xyz/themes/library/mui/css/mui.min.css'>"
				+ "<link rel='stylesheet' type='text/css'	href='http://x13043.sanby.xyz/themes/wechat/test-theme/default-skin/src/css/common.css'>");
		sb.append("<link rel='stylesheet' type='text/css' href='http://x13043.sanby.xyz/themes/wechat/test-theme/default-skin/src/css/book.css'>");
		sb.append("<link rel='stylesheet' type='text/css' href='http://x13043.sanby.xyz/themes/wechat/test-theme/default-skin/src/css/bookcategory.css'>"
				+ "</head><body>");
		return sb.toString();
	}
	
	
}
