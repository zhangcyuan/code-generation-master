package com.lance.code.download.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lance.code.generation.utils.DownLoadImgaeUtil;
import com.lance.code.generation.utils.TxtUtil;

/**
* 漫画下载
* @author zhangchangyuan
* @version 创建时间：2018年2月2日 上午9:50:52
*/
public class ManhuaTest {
	
	//域名
	static String domain = "http://x13043.sanby.xyz";
	//书籍列表
	static String url = domain+"/category/bookList?end=0&category_id=0&query=base"; 
	
	//图片下载路径
    static String filePath ="E:/HaimaApp/manhua/";
	//static String filePath = "D:/imagelist/manhua/";
    
    //书籍信息 地址
    static String bookPath = filePath+"static/book.txt";
	
	public static void main(String[] args) throws IOException {
		
		//页码总数
		int pagenum = 1; //20
		
		
		File bookFile = new File(bookPath);
		if(!bookFile.exists()){
			bookFile.createNewFile();
		}
		
		String lastbooks = TxtUtil.readTxt(bookPath);
		
		Type mtype = new TypeToken<Map<Integer, Book>>() {
        }.getType();
        Gson gson = new Gson();	
        Map<Integer,Book> bookMap = gson.fromJson(lastbooks,mtype);
		if(bookMap==null){
			bookMap = new HashMap<>();
		}
		for (int i = 1; i <= pagenum; i++) {
			Map<String,String> map =new HashMap<>();
			map.put("p", ""+i);
			map.put("limit", "10");
			map.put("ul", "");
			map.put("order", "book_view DESC,book_uptime DESC");
			String pageUrl = String.format(url, i);
			//System.out.println(pageUrl);
			try{
				Document document = Jsoup.connect(pageUrl)
//				.header("Accept", "*/*")
//				.header("Accept-Encoding", "gzip, deflate, br")
//				.header("accept-language", "zh-CN,zh;q=0.9")
//				.header("pragma", "no-cache")
//				.header("Content-Type", "application/x-www-form-urlencoded")
//				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Cookie", "link_follow_chapter_num=72hvvHQ%2Bt5v2JWWCEV7fkG0%3D; PHPSESSID=rf57ufnt3eoutr9v6nmfcj0qd3; x13043_user=M8J%2FmiWv6KByoopW4kUz9ldR1QEjkf0r1WE8xdJzlobJDSWnr%2BHjVlIoBGVv9wF99X0SSEYvO7YR77I9xz8OBILf%2BBLAwfauVcMqSNZuzsXHodmVpwcMk%2FRfMrlsu9ujvyJrEIYtT7XGM9rv1auuasqb%2B6O3F%2Bwsp%2F2bvVWoO3yo1zU%2BS3cJEIcm7Oe3mCKmHYv0bdjh3T56H6AsKQAy%2F95Hm4HFIZ4g8CSrMort1N3%2FvM4iImrTRMRiJ%2F6LxQd4cyudKLy1C7bb1xrYXpzJqq5%2F%2F%2FLL4KRock8uTEA5z8RCVIoGBgYOXsW5sYoD5VQbuwJAJKghRIh5WIO%2FwsdqI2rxHrbmlGp7mYitlmighXHSWK8HdT7j")
				.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A366 MicroMessenger/6.7.2 NetType/WIFI Language/zh_CN")
//				.header("referer", "https://pro.m.jd.com/mall/active/ggTAyJ5g3o8QQdD7CAwM4m2siZ8/index.html?has_native=0&cu=true&utm_source=url.cn&utm_medium=tuiguang&utm_campaign=t_2009631446_&utm_term=6a05032ec4a845d3a7e78f259a46dd25&abt=3&sid=d9a7847353ed06b17e43856e66553403")
				.data(map)
				//&p=1&limit=10&ul=&order=book_view+DESC,book_uptime+DESC
				.ignoreContentType(true).post();
				if(StringUtils.isEmpty(document.body())){
					continue;
				}
				//System.out.println(document.body());
				
				Elements li =	document.select("li");
				for (Element element : li) {
					String href = element.selectFirst("a").attr("href");
					String bookname = element.select(".w-bookName").text();
					String updateNum =  element.select(".classifly-chapter").text();
					String head_pic =  element.select("img").attr("src");
					
					System.out.println(bookname);
//					System.out.println(updateNum);
					Integer bookId =Integer.parseInt(href.replace("/book/cartoon/info?book_id=", ""));
					int newNum = Integer.parseInt(updateNum.replace("更至第", "").replace("话", ""));
					Book book = bookMap.get(bookId);
					
					
					if(book==null){
						book = new Book();
						book.setBookid(bookId);
						book.setBookname(bookname);
						book.setHref(href);
						//新书则爬所有章节
						book.setUpdateNum(0);
					}
					//头图下载
					//http://img.fox800.xyz/books/1528788134_1524466221_xxxxxx-210x297.jpg
					DownLoadImgaeUtil.downImages(filePath, head_pic,null ,filePath+book.getBookid()+"_"+book.getBookname()+"/",book.getBookname()+".jpg");
					//旧书爬取更新的章节
					//parseBookDetail(book);
					
					book.setUpdateNum(newNum);
					
					bookMap.put(bookId, book);
					String json = gson.toJson(bookMap);
					TxtUtil.writeTxt(bookPath, json);
					//Thread.sleep(1000L);
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
			
			
		}
	
	
	/**
	 * 读取书籍详情页
	 * @param book
	 * @param bookId
	 */
	public static void parseBookDetail(Book book){
		try{
			Document document = Jsoup.connect(domain+book.getHref())
			.header("Cookie", "link_follow_chapter_num=72hvvHQ%2Bt5v2JWWCEV7fkG0%3D; PHPSESSID=rf57ufnt3eoutr9v6nmfcj0qd3; x13043_user=M8J%2FmiWv6KByoopW4kUz9ldR1QEjkf0r1WE8xdJzlobJDSWnr%2BHjVlIoBGVv9wF99X0SSEYvO7YR77I9xz8OBILf%2BBLAwfauVcMqSNZuzsXHodmVpwcMk%2FRfMrlsu9ujvyJrEIYtT7XGM9rv1auuasqb%2B6O3F%2Bwsp%2F2bvVWoO3yo1zU%2BS3cJEIcm7Oe3mCKmHYv0bdjh3T56H6AsKQAy%2F95Hm4HFIZ4g8CSrMort1N3%2FvM4iImrTRMRiJ%2F6LxQd4cyudKLy1C7bb1xrYXpzJqq5%2F%2F%2FLL4KRock8uTEA5z8RCVIoGBgYOXsW5sYoD5VQbuwJAJKghRIh5WIO%2FwsdqI2rxHrbmlGp7mYitlmighXHSWK8HdT7j")
			.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A366 MicroMessenger/6.7.2 NetType/WIFI Language/zh_CN")
			.ignoreContentType(true).get();
			//System.out.println(document.body());
			
			//找到主图链接
			String mainImage =	document.select(".l-top-bg img").get(0).attr("src");
			//阅读数
			String readCount =	document.select(".ft-content h1").get(0).text();
			System.out.println(readCount);
			//标签 分类
			Elements tagesEle =	document.select(".ft-content-follow span");
			
			//更新状态
			String updateState =	document.select(".l-detail-span").get(0).text();
			
			Elements chapters = document.select("ul>li>p.overhidden");
			int index = 0;
			List<Chapter> list = new ArrayList<>();
			for (Element element : chapters) {
				Chapter cp = new Chapter();
				index++;
				String href = element.attr("onclick").replace("window.location.href = '", "").replace("'", "");
				cp.setChapter_id(href.split("=")[2]);
				cp.setSort(index);
				cp.setTitle(element.text());
				cp.setHref(href);
				list.add(cp);
				//判断章节大于上次章节 爬出图片
				if(index>book.getUpdateNum()){
					parseChapterDetail(book, cp);
					Thread.sleep(1000L);
				}
			}
			//book.setChapters(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取章节信息 并下载图片
	 * @param book
	 * @param cp
	 */
	public static void parseChapterDetail(Book book,Chapter cp){
		try{
			Document document = Jsoup.connect(domain+cp.getHref())
			.header("Cookie", "link_follow_chapter_num=72hvvHQ%2Bt5v2JWWCEV7fkG0%3D; PHPSESSID=rf57ufnt3eoutr9v6nmfcj0qd3; x13043_user=M8J%2FmiWv6KByoopW4kUz9ldR1QEjkf0r1WE8xdJzlobJDSWnr%2BHjVlIoBGVv9wF99X0SSEYvO7YR77I9xz8OBILf%2BBLAwfauVcMqSNZuzsXHodmVpwcMk%2FRfMrlsu9ujvyJrEIYtT7XGM9rv1auuasqb%2B6O3F%2Bwsp%2F2bvVWoO3yo1zU%2BS3cJEIcm7Oe3mCKmHYv0bdjh3T56H6AsKQAy%2F95Hm4HFIZ4g8CSrMort1N3%2FvM4iImrTRMRiJ%2F6LxQd4cyudKLy1C7bb1xrYXpzJqq5%2F%2F%2FLL4KRock8uTEA5z8RCVIoGBgYOXsW5sYoD5VQbuwJAJKghRIh5WIO%2FwsdqI2rxHrbmlGp7mYitlmighXHSWK8HdT7j")
			.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A366 MicroMessenger/6.7.2 NetType/WIFI Language/zh_CN")
			.ignoreContentType(true).get();
			//System.out.println(document.body());
			Elements images = document.select("#detail-img-content>img");
			for (Element element : images) {
				String img_url = element.attr("data-lazyload");
				img_url = img_url.substring(0, img_url.indexOf("?"));
				//http://img.fox800.xyz/images/book_43_chapter_1993_22.jpg
				DownLoadImgaeUtil.downImages(filePath, img_url, "http://img.fox800.xyz/images/",filePath+book.getBookid()+"_"+book.getBookname()+"/"+cp.getChapter_id()+"_"+cp.getTitle()+"/",null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
