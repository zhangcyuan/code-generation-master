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
			makeMulu(book);
			//生成每个章节的漫画内容
			break;
		}
		//读取首页模板
		String bookHome = TxtUtil.readTxt(ManhuaTest.filePath+"static/home.html");
		System.out.println(bookHome);
		//4.保存html文件到主目录  主页
		TxtUtil.writeTxt(ManhuaTest.filePath+"home.html", bookHome.replace("【书籍列表】", ul.toString()));
		
		
		
		
	}
	
	public static void makeMulu(Book book){
		String bookPage = TxtUtil.readTxt(ManhuaTest.filePath+"static/page.html");
		bookPage = bookPage.replaceAll("【漫画书名】", book.getBookname());
		bookPage = bookPage.replace("【阅读量】", book.getReadCount());
		bookPage = bookPage.replace("【更新状态】", book.getUpdateState());
		String tags = "";
		for (String tag : book.getBookTag()) {
			tags+="<span>"+tag+"</span>";
		}
		bookPage = bookPage.replace("【标签分类】", tags);
		bookPage = bookPage.replace("【更新章节序号】", book.getUpdateNum()+"");
		
		/**
		 *  <li onclick="window.location.href = '/book/cartoon/read?book_id=43&amp;chapter_id=1993'">
                  <div class="orderid" data-chapter="1"></div>
                  <p class="overhidden" onclick="window.location.href = '/book/cartoon/read?book_id=43&amp;chapter_id=1993'">第1话 获取能力</p></li>
		 */
		StringBuffer liSb = new StringBuffer();
		liSb.append("<li><div class='orderid' data-chapter='【章节序号】'></div>");
		liSb.append("<p class='overhidden' onclick=\"window.location.href = '【章节内容】'\">【章节名称】</p></li>");
		
		//读取书下所有目录取名称
		File file=new File(ManhuaTest.filePath+"image/"+book.getBookpath());
		File[] files =	file.listFiles();
		int i =0;
		StringBuffer ul = new StringBuffer();
		for (File file2 : files) {
			if(file2.isDirectory()){
				i++;
				String li = liSb.toString();
				String chapterName =file2.getName().split("_")[1];
				String chapterId = file2.getName().split("_")[0];
				li = li.replace("【章节序号】", i+"");
				li = li.replace("【章节内容】", "./"+i+".html");
				li = li.replace("【章节名称】", chapterName);
				ul.append(li);
				
				//生成章节内容页
				makeContext(files, file2, book, chapterName, i);
			}
		}
		
		bookPage = bookPage.replace("【书籍目录】", ul.toString());
		TxtUtil.writeTxt(ManhuaTest.filePath+"image/"+book.getBookpath()+"page.html", bookPage);
		
	}
	
	
	
	/**
	 * 
	 * @param files 章节目录文件
	 * @param thisFile 当前章节文件
	 * @param book 书籍信息
	 * @param chapterName  章节名称
	 * @param i  章节序号
	 */
	public static void makeContext(File[] files,File thisFile,Book book,String chapterName,int i){
		String bookPage = TxtUtil.readTxt(ManhuaTest.filePath+"static/manhua.html");
		bookPage = bookPage.replaceAll("【章节名称】", chapterName);
		bookPage = bookPage.replace("【更新状态】", book.getUpdateState());
		bookPage = bookPage.replace("【更新章节序号】", book.getUpdateNum()+"");
		
		
		/**
		 * <li class="is-active"><a class="orderid" data-chapter="【章节排序】" href="【章节排序】.html" >【章节排序】</a></li>
		 */
		
		StringBuffer muluSb = new StringBuffer();
		int index = 0;
		for (File file : files) {
			if(file.isDirectory()){
				index++;
				//is-active
				String muluLi = "<li class='【当前章节】' onclick=\"window.location.href='./【章节排序】.html'\"><a class='orderid' data-chapter='【章节排序】' href='#'>【章节排序】</a></li>";
				muluLi = muluLi.replaceAll("【章节排序】", index+"");
				if(index==i){
					muluLi = muluLi.replace("【当前章节】", "is-active");
				}
				muluSb.append(muluLi);
			}
		}
		bookPage = bookPage.replace("【章节目录】", muluSb.toString());
		
		
		/**
		 * <img data-original="http://img.fox800.xyz/images/149/1535089121_book_149_chapter_6962_12.jpg" src="../../static/default.jpg" width="100%" data-lazyload-id="0">
		 */
		//D:\imagelist\manhua\image\43_超级吸引力\1993_第1话 获取能力
		StringBuffer imgsb =new StringBuffer();
		File[] imageFiles =	thisFile.listFiles();
		for (int j = 0; j < imageFiles.length; j++) {
			File image = imageFiles[j];
			String img = "<img data-original='./"+thisFile.getName()+"/"+image.getName()+"' src='../../static/default.jpg' width='100%' data-lazyload-id='"+j+"'>";
			imgsb.append(img);
			
		}
		
		bookPage = bookPage.replace("【图片列表】", imgsb.toString());
		
		if(i==1){
			bookPage = bookPage.replace("【上一地址】", "");
			bookPage = bookPage.replace("【上一章节】", "没有了");
		}else{
			bookPage = bookPage.replace("【上一地址】","onclick=\"window.location.href='./"+(i-1)+".html'\"");
			bookPage = bookPage.replace("【上一章节】", "上一话");
		}
		
		if(i==book.getUpdateNum().intValue()){
			bookPage = bookPage.replace("【下一地址】", "");
			bookPage = bookPage.replace("【下一章节】", "没有了");
		}else{
			bookPage = bookPage.replace("【下一地址】","onclick=\"window.location.href='./"+(i+1)+".html'\"");
			bookPage = bookPage.replace("【下一章节】", "下一话");
		}
	
		TxtUtil.writeTxt(ManhuaTest.filePath+"image/"+book.getBookpath()+i+".html", bookPage);

		
	}
}
