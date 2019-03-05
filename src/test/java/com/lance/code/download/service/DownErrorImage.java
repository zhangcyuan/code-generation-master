package com.lance.code.download.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lance.code.download.service.ManhuaTest;
import com.lance.code.generation.utils.TxtUtil;

public class DownErrorImage {
	
	public static void main(String[] args) {
		 
		
		List<String> list = TxtUtil.readLine("F:/manhua/lose.txt");
		for (String string : list) {
			//http://img.fox800.xyz/books/1528788779_1522653077_QQ截图20180326181403 拷贝.jpg==D:/imagelist/manhua/image/2_爱上男闺蜜/爱上男闺蜜.jpg
			String[] imgs = string.split("==");
			int indexOf = imgs[1].lastIndexOf("/");
			
			String domain = "http://img.fox800.xyz/";
			String param = imgs[0].replace(domain, "");
			String fileName = imgs[1].substring(indexOf+1, imgs[1].length());
			String dirs = imgs[1].substring(0, indexOf+1);
			String imgurl=imgs[0];
			/*try {
				imgurl = domain+URLEncoder.encode(param,"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}*/
			System.out.println(imgurl);
			down(imgurl,dirs,fileName);
		}
		
       
    }  
	
	static void down(String imgUrl,String dirs,String fileName){
		 try {  
			/* String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）\"——+|{}‘；：”“’。，、p]"; 
				Pattern p = Pattern.compile(regEx); 
				Matcher m = p.matcher(dirs);
				dirs =  m.replaceAll("").trim();*/
	            //创建文件目录  
	            File files = new File(dirs);  
	            if (!files.exists()) {  
	                files.mkdirs();  
	            }  
	            String lastFilePath = dirs+fileName;
	            
	            File file = new File(lastFilePath);
	            if(file.exists()){
	            	return;
	            }
	            
	            //获取下载地址  
	            URL url = new URL(imgUrl);  
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
	        	System.out.println(imgUrl);
	            e.printStackTrace();  
	        }finally {
				try {
					Thread.sleep(10L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
		
		
	}
	
}
