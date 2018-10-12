package com.lance.code.download.service;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lance.code.generation.utils.TxtUtil;

public class MakeManhuaHome {
	static String NEWLINE = "\n";
	
	
	public static void main(String[] args) {
		//1.读取book.txt文件
		String lastbooks = TxtUtil.readTxt(ManhuaTest.bookPath);
		Gson gson = new Gson();	
		Type mtype = new TypeToken<Map<Integer, Book>>() {
        }.getType();
	    Map<Integer,Book> bookMap = gson.fromJson(lastbooks,mtype);
		//2.生成每一本书的信息
	    //3.组装到html文件内容中
		StringBuffer liSb = new StringBuffer();
		/**
		 *  <li class="w-shelfBookinfo">
                      <a href="/book/cartoon/info?book_id=43">
                        <div class="w-bookPic">
                          <img src="http://img.fox800.xyz/books/1528788134_1524466221_超级吸引力-210x297.jpg">
                          <div class="classifly-chapter">更至第75话</div></div>
                        <div class="w-bookName">超级吸引力</div></a>
                    </li>
		 */
		liSb.append("<li class='w-shelfBookinfo'>").append("\n");
		liSb.append("<a href='【书籍主页】'>").append("\n");
		liSb.append("<div class='w-bookPic'>").append(NEWLINE);
		liSb.append("<img src='【封面图片】'>").append(NEWLINE);
		liSb.append("<div class='classifly-chapter'>更至第【更新章节序号】话</div></div>").append(NEWLINE);
		liSb.append("<div class='w-bookName'>【漫画书名】</div></a>").append(NEWLINE);
		liSb.append("</li>").append(NEWLINE);
		  
		StringBuffer ul = new StringBuffer();
		for (Integer bookId : bookMap.keySet()) {
			Book book = bookMap.get(bookId);
			String li = liSb.toString(); 
			li = li.replace("【书籍主页】", "./image/"+book.getBookid()+"_"+book.getBookname()+"/page.html");
			li = li.replace("【封面图片】", "./image/"+book.getBookid()+"_"+book.getBookname()+"/"+book.getBookname()+".jpg");
			li = li.replace("【更新章节序号】", book.getUpdateNum()+"");
			li = li.replace("【漫画书名】", book.getBookname());
			ul.append(li);
			//生成每本书的章节目录
			//makeMulu(book);
			//生成每个章节的漫画内容
			break;
		}
		System.out.println(ul);
		//读取首页模板
		String bookHome = TxtUtil.readTxt(ManhuaTest.filePath+"static/home.html");
		System.out.println(bookHome);
		//4.保存html文件到主目录  主页
		TxtUtil.writeTxt(ManhuaTest.filePath+"home.html", bookHome.replace("【书籍列表】", ul.toString()));
		
		
		
		
	}
	
	public static void makeMulu(Book book){
		StringBuffer sb = new StringBuffer();
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
				
				
			}
		}
		
		sb.append("</ul></body></html>");
		System.out.println(sb);
		
		TxtUtil.writeTxt(ManhuaTest.filePath+book.getBookid()+"_"+book.getBookname()+"/page.html", sb.toString());
		
	}
	
	
	
	
	
	

	
	
}
