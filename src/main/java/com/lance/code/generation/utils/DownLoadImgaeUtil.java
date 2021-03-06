package com.lance.code.generation.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.lance.code.download.service.ManhuaTest;

public class DownLoadImgaeUtil {
	
	/**
	 * 
	 * @param filePath 跟路径 一般和域名 替换
	 * @param imgUrl  图片地址
	 * @param replaceStr 一般是域名  替换成跟路径地址
	 * @param userPath	自定义目录地址
	 * @param fileName 自定义文件名   如果2个自定义使用了   上面的 跟路径可以不需要
	 * @throws UnsupportedEncodingException
	 */
	public static void downImages(String filePath,String imgUrl,String replaceStr,String userPath,String fileName) 
			throws UnsupportedEncodingException {  
		//http://tuigirl-1254818389.cosbj.myqcloud.com/picture/playboy/286/0.jpg
		//E:/HaimaApp/aisipic/playboy/286/0.jpg
        //图片url中的前面部分：例如"http://images.csdn.net/"  
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
            //创建文件目录  
            File files = new File(dirs);  
            if (!files.exists()) {  
                files.mkdirs();  
            }  
            File file = new File(lastFilePath);
            if(file.exists()){
            	return;
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
        	System.out.println(imgUrl);
        	TxtUtil.addTxt(ManhuaTest.filePath+"main.txt", imgUrl+"=="+lastFilePath);
            e.printStackTrace();  
        }finally {
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
    }  
	
	public static void main(String[] args) {
		try {
			DownLoadImgaeUtil.downImages(null, "http://img.fox800.xyz/books/1545028656_1515464336_情敌不好惹%20%20横版封面(无字）.jpg", null, "F:/imagelist/manhua/image/367_莫少逼婚，新妻难招架/", "莫少逼婚，新妻难招架_main.jpg");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
