package com.lance.code.download.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.lance.code.generation.utils.DownLoadImgaeUtil;
import com.lance.code.generation.utils.TxtUtil;

public class ManhuaThread extends Thread{
	private Book book;
	
	private CountDownLatch threadsSignal;
	
	public ManhuaThread(Book book,CountDownLatch threadsSignal) {
		super();
		this.book = book;
		this.threadsSignal =threadsSignal;
	}
	
	

	@Override
	public void run() {
		parseBookDetail(ManhuaTest.bookMap, book);
	}
	
//	public static void main(String[] args) {
//		Book book = new Book();
//		//book.setBookpath(bookpath);
//		new ManhuaThread(book, null).run();
//	}
	
	
	/**
	 * 读取书籍详情页
	 * @param book
	 * @param bookId
	 */
	public void parseBookDetail(Map<Integer,Book> bookMap,Book book){
		// System.out.println("开始了线程" + threadsSignal.getCount());
		try{
			Document document = Jsoup.connect(ManhuaTest.domain+book.getHref())
			.header("Cookie", "link_follow_chapter_num=72hvvHQ%2Bt5v2JWWCEV7fkG0%3D; PHPSESSID=rf57ufnt3eoutr9v6nmfcj0qd3; x13043_user=M8J%2FmiWv6KByoopW4kUz9ldR1QEjkf0r1WE8xdJzlobJDSWnr%2BHjVlIoBGVv9wF99X0SSEYvO7YR77I9xz8OBILf%2BBLAwfauVcMqSNZuzsXHodmVpwcMk%2FRfMrlsu9ujvyJrEIYtT7XGM9rv1auuasqb%2B6O3F%2Bwsp%2F2bvVWoO3yo1zU%2BS3cJEIcm7Oe3mCKmHYv0bdjh3T56H6AsKQAy%2F95Hm4HFIZ4g8CSrMort1N3%2FvM4iImrTRMRiJ%2F6LxQd4cyudKLy1C7bb1xrYXpzJqq5%2F%2F%2FLL4KRock8uTEA5z8RCVIoGBgYOXsW5sYoD5VQbuwJAJKghRIh5WIO%2FwsdqI2rxHrbmlGp7mYitlmighXHSWK8HdT7j")
			.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A366 MicroMessenger/6.7.2 NetType/WIFI Language/zh_CN")
			.ignoreContentType(true).get();
			//System.out.println(document.body());
			
			//找到主图链接
			String mainImage =	document.select(".hd img").get(0).attr("src");
			this.downImages(null, mainImage,null ,ManhuaTest.filePath+"image/"+book.getBookpath(),book.getBookname()+"_main.jpg");
			
			//阅读数
			String readCount =	document.select(".ft-content h1").get(0).text();
			book.setReadCount(readCount);
			List<String> tagList = new ArrayList<>();
			//标签 分类
			Elements tags =	document.select(".ft-content-follow span");
			for (Element element : tags) {
				tagList.add(element.text());
			}
			book.setBookTag(tagList);
			//更新状态
			String updateState =	document.select(".l-detail-span").get(0).text();
			book.setUpdateState(updateState);
			
			Gson gson = new Gson();	
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
				if(index>book.getDownloadNum()){
					
					parseChapterDetail(book, cp);
					book.setDownloadNum(index);
					bookMap.put(book.getBookid(), book);
					String json = gson.toJson(bookMap);
					TxtUtil.writeTxt(ManhuaTest.bookPath, json);
					System.out.println("下载完毕：==第"+book.getPageNum()+"页=="+book.getBookid()+"=="+book.getBookname()+"===="+cp.getTitle());
				}
			}
			
		} catch (Exception e) {
			TxtUtil.addTxt(ManhuaTest.filePath+"book.txt", book.getPageNum()+"=="+book.getHref()+"=="+book.getBookname()+"=="+book.getBookpath());
			e.printStackTrace();
		}finally {
//			threadsSignal.countDown();
//			System.out.println(Thread.currentThread().getName() + "结束. 还有"
//				     + threadsSignal.getCount() + " 个线程");
		}
	}
	
	
	public static void main(String[] args) {
		ManhuaThread thread = new ManhuaThread(null, null);
		List<String> stringlist =	TxtUtil.readLine(ManhuaTest.filePath+"chapter.txt");
		for (String string : stringlist) {
			try {
				String[] infos =	string.split("==");
				Book book = new Book();
				book.setPageNum(Integer.parseInt(infos[0]));
				book.setBookname(infos[2]);
				book.setBookid(Integer.parseInt(infos[1]));
				
				Chapter cp = new Chapter();
				cp.setChapter_id(infos[4]);
				cp.setTitle(infos[5]);
				cp.setHref(infos[3]);
				thread.parseChapterDetail(book, cp);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(string);
			}
			
		}
		
	}
	
	/**
	 * 读取章节信息 并下载图片
	 * @param book
	 * @param cp
	 */
	public void parseChapterDetail(Book book,Chapter cp){
		String url = "";
		if(cp.getHref().contains(ManhuaTest.domain)){
			url = cp.getHref();
		}else{
			url = ManhuaTest.domain+cp.getHref();
		}
		
		try{
			System.out.println("开始下载：==第"+book.getPageNum()+"页=="+book.getBookid()+"=="+book.getBookname()+"=="+cp.getTitle());
			Document document = Jsoup.connect(url)
			.header("Cookie", "link_follow_chapter_num=72hvvHQ%2Bt5v2JWWCEV7fkG0%3D; PHPSESSID=rf57ufnt3eoutr9v6nmfcj0qd3; x13043_user=M8J%2FmiWv6KByoopW4kUz9ldR1QEjkf0r1WE8xdJzlobJDSWnr%2BHjVlIoBGVv9wF99X0SSEYvO7YR77I9xz8OBILf%2BBLAwfauVcMqSNZuzsXHodmVpwcMk%2FRfMrlsu9ujvyJrEIYtT7XGM9rv1auuasqb%2B6O3F%2Bwsp%2F2bvVWoO3yo1zU%2BS3cJEIcm7Oe3mCKmHYv0bdjh3T56H6AsKQAy%2F95Hm4HFIZ4g8CSrMort1N3%2FvM4iImrTRMRiJ%2F6LxQd4cyudKLy1C7bb1xrYXpzJqq5%2F%2F%2FLL4KRock8uTEA5z8RCVIoGBgYOXsW5sYoD5VQbuwJAJKghRIh5WIO%2FwsdqI2rxHrbmlGp7mYitlmighXHSWK8HdT7j")
			.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A366 MicroMessenger/6.7.2 NetType/WIFI Language/zh_CN")
			.ignoreContentType(true).get();
			//System.out.println(document.body());
			Elements images = document.select("#detail-img-content>img");
			for (Element element : images) {
				String img_url = element.attr("data-lazyload");
				img_url = img_url.substring(0, img_url.indexOf("?"));
				
				int indexOf = img_url.lastIndexOf("/");
				String fileName = img_url.substring(indexOf+1, img_url.length());
				
				String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\]./<>?~！@#￥%……&*\"（）——+|{}‘；：”“’。，、p]"; 
				Pattern p = Pattern.compile(regEx); 
				Matcher m = p.matcher(cp.getTitle());
				String title =  m.replaceAll("");
				this.downImages(null, img_url, null,ManhuaTest.filePath+"image/"+book.getBookid()+"_"+book.getBookname().trim()+"/"+cp.getChapter_id()+"_"+(title.trim())+"/",fileName.trim());
				Thread.sleep(20L);
			}
			
		} catch (Exception e) {
			TxtUtil.addTxt(ManhuaTest.filePath+"chapter.txt", book.getPageNum()+"=="+book.getBookid()+"=="+book.getBookname()+"=="+url+"=="+cp.getChapter_id()+"=="+cp.getTitle());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	public void downImages(String filePath,String imgUrl,String replaceStr,String userPath,String fileName) 
			throws UnsupportedEncodingException {  
		String lastFilePath ="";
		String dirs = "";
		if(userPath==null){
			lastFilePath = imgUrl.replace(replaceStr, filePath);
			dirs = lastFilePath.substring(0, lastFilePath.lastIndexOf("/"));
		}else{
			if(fileName!=null){
				lastFilePath = userPath+fileName;
			}else{
				lastFilePath = imgUrl.replace(replaceStr, userPath);				
			}
			dirs = userPath;
		}
		
        try {  
	    	 File file = new File(lastFilePath);
	          if(file.exists()){
	          	return;
	         }
            //创建文件目录  
            File files = new File(dirs);  
            if (!files.exists()) {  
                files.mkdirs();  
            }  
            String domain = "";
            String param = "";
            int firstX  = 0 ;
            if(imgUrl.contains("http")){
            	firstX  = imgUrl.replace("://", "").indexOf("/");
            	domain =  imgUrl.substring(0, firstX+4);
            	param =  imgUrl.substring(firstX+4,imgUrl.length());
            }else{
            	firstX = imgUrl.indexOf("/");
            	domain =  imgUrl.substring(0, firstX+1);
            	param =  imgUrl.substring(firstX+1,imgUrl.length());
            }
            String downurl = domain + URLEncoder.encode(param,"utf-8");
            //获取下载地址  
            URL url = new URL(downurl); 
            //链接网络地址  
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();  
            connection.setReadTimeout(30*1000);
            //获取链接的输出流  
            InputStream is = connection.getInputStream();  
            //创建文件，fileName为编码之前的文件名  
           
            //根据输入流写入文件  
            FileOutputStream out = new FileOutputStream(file);  
            int i = 0;  
            while((i = is.read()) != -1){  
                out.write(i);  
            }  
            out.close();  
            is.close();  
        } catch (Exception e) {  
        	e.printStackTrace();
        	TxtUtil.addTxt(ManhuaTest.filePath+"lose.txt", imgUrl+"=="+lastFilePath);
        	System.out.println(imgUrl);
            e.printStackTrace();  
        }  
    }  
}
