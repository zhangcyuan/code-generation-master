package com.lance.code.generation.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class TxtUtil {
	/**
	 * 读取TXT文件
	 * @param filePath
	 * @return
	 */
	public static String readTxt(String filePath){
		StringBuffer sb = new StringBuffer();  
		try {
			//绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
	        File filename = new File(filePath); // 要读取以上路径的input。txt文件  
	        InputStreamReader reader = new InputStreamReader(  
	                new FileInputStream(filename)); // 建立一个输入流对象reader  
	        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        
	        String line ="";
	        line = br.readLine();  
	        while(line != null) {  
	        	sb.append(line);
	        	// 一次读入一行数据  
	        	line = br.readLine();  
	        }
	        br.close();
	        reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return sb.toString(); 
	}
	
	public static void writeTxt(String filePath,String context){
		try {
			 /* 写入Txt文件 */  
            File writename = new File(filePath); // 相对路径，如果没有则要建立一个新的output。txt文件  
            if(!writename.exists()){
            	 writename.createNewFile(); // 创建新文件  
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
            out.write(context); // \r\n即为换行  
            out.flush(); // 把缓存区内容压入文件  
            out.close(); // 最后记得关闭文件 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addTxt(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(conent+"\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static List<String> readLine(String filePath){
		List<String> list = new ArrayList<>();
		try {
			//绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
	        File filename = new File(filePath); // 要读取以上路径的input。txt文件  
	        InputStreamReader reader = new InputStreamReader(  
	                new FileInputStream(filename)); // 建立一个输入流对象reader  
	        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        
	        String line ="";
	        line = br.readLine();  
	        while(line != null) {  
	        	list.add(line);
	        	// 一次读入一行数据  
	        	line = br.readLine();  
	        }
	        br.close();
	        reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return list;
	}
	
	
	

}
