package com.lance.code.download.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
*
* @author zhangchangyuan
* @version 创建时间：2018年2月6日 下午1:45:50
*/
public class DownloadFileTest {

	
	public static void main(String[] args) {
		String imgUrl = "http://down.aiss8.net/FZST/[AISS8.com]F系列钻石套图 F5036 美丝学校交作业[77P102M].rar";
		String lastFilePath = "E:/HaimaApp/aiss/[AISS8.com]F系列钻石套图 F5036 美丝学校交作业[77P102M].rar";
		String filePath ="E:/HaimaApp/aiss/";
        try {  
            //创建文件目录  
            File files = new File(filePath);  
            if (!files.exists()) {  
                files.mkdirs();  
            }  
            //获取下载地址  
            URL url = new URL(imgUrl);  
            //链接网络地址  
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();  
            //获取链接的输出流  
            InputStream is = connection.getInputStream();  
            //创建文件，fileName为编码之前的文件名  
            File file = new File(lastFilePath);  
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
        }  
	}
}
