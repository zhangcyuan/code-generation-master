package com.lance.code.download.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

/**
* 漫画下载
* @author zhangchangyuan
* @version 创建时间：2018年2月2日 上午9:50:52
*/
public class ManhuaTest {
	
	public static void main(String[] args) {
		//分类
		String url = "http://x13043.sanby.xyz/category/bookList?end=0&category_id=0&query=base"; 
		//页码总数
		int pagenum = 1; //20
		//图片总数
		int counts = 0;
		//图片下载路径
		String filePath ="E:/HaimaApp/manhua/";
		
		
		
		Map<Integer,Book> bookMap = new HashMap<>();
		
		
		
		
		
		
		for (int i = 1; i <= pagenum; i++) {
			Map<String,String> map =new HashMap<>();
			map.put("p", ""+i);
			map.put("limit", "10");
			map.put("ul", "");
			map.put("order", "book_view DESC,book_uptime DESC");
			String pageUrl = String.format(url, i);
			System.out.println(pageUrl);
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
				//System.out.println(document.body());
				
				
				Elements li =	document.select("li");
				for (Element element : li) {
					String href = element.selectFirst("a").attr("href");
					String bookname = element.select(".w-bookName").text();
					String updateNum =  element.select(".classifly-chapter").text();
					//System.out.println(element.toString());
					System.out.println(href);
					System.out.println(bookname);
					System.out.println(updateNum);
					Book book = new Book();
					book.setBookname(bookname);
					book.setUpdateNum(Integer.parseInt(updateNum.replace("更至第", "").replace("话", "")));
					book.setHref(href);
					book.setBookid(Integer.parseInt(href.replace("/book/cartoon/info?book_id=", "")));
					bookMap.put(book.getBookid(), book);
				}
//				System.out.println(response);
//				Gson gson = new Gson();	
//				Map<String,Object> map = gson.fromJson(response, Map.class);
//				Map<String, Object> data = (Map)map.get("data");
//				List<Map<String,Object>> list = (List) data.get("list");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println("last size = "+counts);
		}
			
			
			
		}
	
}
