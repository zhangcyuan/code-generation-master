package com.lance.code.jsoup.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JustTest {
	public static void main(String[] args) {
//	URL url = null;
		   HttpURLConnection conn = null;
		   try{
			   String content = "<request><header><accountId>klwlxcapi</accountId><serviceName>CreateOrder</serviceName><requestTime>2018-01-19 14:50:00</requestTime><version>2.0</version><sign>9403f3117a366e1a054e502a6609c086</sign></header><body><otaOrderId>ctriptest-133187</otaOrderId><productId>5145123</productId><otaProductName><![CDATA[飞霞山门票]]></otaProductName><price>0.10</price><count>1</count><contactName>张小雷</contactName><contactMobile></contactMobile><contactIdCardType>1</contactIdCardType><contactIdCardNo></contactIdCardNo><remark></remark><passengerInfos></passengerInfos><useDate>2018-01-19</useDate><useEndDate>2018-01-19</useEndDate><payMode>1</payMode><extendInfo></extendInfo></body></request>";
		    //HTTP的请求URL

			URL url = new URL("http://api.kailly.com/api/xc/orderhandler");
		    conn = (HttpURLConnection)url.openConnection();
//		    conn.setRequestProperty("Pragma:", "no-cache");
		    conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		    //HTTP的POST方式进行数据传输
		    conn.setDoInput(true);
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
		    OutputStream dataOut = conn.getOutputStream();
		    StringBuffer sendStr = new StringBuffer(1024);
		    dataOut.write(content.getBytes());
		    dataOut.write(sendStr.toString().getBytes());
		    dataOut.flush();
		    dataOut.close();
		    //获取响应内容
		   try{
		     conn.connect();

		     BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		     //注意BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK")); 这么写，是因为有时候网络传输过程中字符会被修改,如GB-2312，因为出现乱码,就需要将此处加入GBK。
		    //loger.info("获取默认字符编码："+Charset.defaultCharset());
		    String line = "";
		    StringBuffer buffer = new StringBuffer(1024);
		    while((line=in.readLine())!=null){
		     buffer.append(line);
		    }
		    in.close();
		    String result = buffer.toString();
		    System.out.println(result);
		     //获取到的回馈信息:+result+结果集。
		    }catch(IOException e){
		     e.printStackTrace();
		    }catch(Exception e){
		     e.printStackTrace();
		    }
		   }catch(Exception e){
		    e.printStackTrace();
		   }

		}
	
	
}
