package com.lance.code.thread.service;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

/**
*
* @author zhangchangyuan
* @version 创建时间：2018年5月2日 下午3:37:05
*/
public class YybkRunable implements Runnable {
	
	private String url;
	private String name;
	
	
	

	@Override 
	public  void run() {
		
	   XSSFWorkbook wb = new XSSFWorkbook();
	   XSSFSheet sheet = wb.createSheet();
	   XSSFCellStyle style = wb.createCellStyle();
	   /*
	   String path = "D:/cookbook/"+name+".xlsx";	
	   File newFile = new File(path);
	   if(newFile.exists()){
			newFile.delete();
	   }
	 
	   
       style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
       style.setBorderTop(XSSFCellStyle.BORDER_THIN);
       style.setBorderRight(XSSFCellStyle.BORDER_THIN);
       style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
       style.setVerticalAlignment(VerticalAlignment.CENTER);
       style.setAlignment(HorizontalAlignment.LEFT);
      
		writeStringToCell(sheet, 0, 0, "名称", 0,style);
		sheet.setColumnWidth(0, 4000);
		writeStringToCell(sheet, 0, 1, "食物分类", 0,style);
		sheet.setColumnWidth(1, 3000);
		writeStringToCell(sheet, 0, 2, "描述", 0,style);
		sheet.setColumnWidth(2, 9000);
		writeStringToCell(sheet, 0, 3, "孕妇", 0,style);
		sheet.setColumnWidth(3, 3000);
		writeStringToCell(sheet, 0, 4, "孕妇描述", 0,style);
		sheet.setColumnWidth(4, 9000);
		writeStringToCell(sheet, 0, 5, "产妇", 0,style);
		writeStringToCell(sheet, 0, 6, "产妇描述", 0,style);
		sheet.setColumnWidth(6, 9000);
		writeStringToCell(sheet, 0, 7, "哺乳期", 0,style);
		writeStringToCell(sheet, 0, 8, "哺乳期描述", 0,style);
		sheet.setColumnWidth(8, 9000);
		writeStringToCell(sheet, 0, 9, "婴儿", 0,style);
		writeStringToCell(sheet, 0, 10, "婴儿描述", 0,style);
		sheet.setColumnWidth(10, 9000);
		writeStringToCell(sheet, 0, 11, "孕妇同类食物", 0,style);
		sheet.setColumnWidth(11, 9000);
		writeStringToCell(sheet, 0, 12, "产妇同类食物", 0,style);
		sheet.setColumnWidth(12, 9000);
		writeStringToCell(sheet, 0, 13, "哺乳期同类食物", 0,style);
		sheet.setColumnWidth(13, 9000);
		writeStringToCell(sheet, 0, 14, "婴儿同类食物", 0,style);
		sheet.setColumnWidth(14, 9000);*/
		
		
		
		
		
		int index = 1;
		try {
			Document doc= Jsoup.connect(url).get();
			index = selectFields(doc,sheet,style,index);
			while (true) {
				//查询页数
				Elements next=	doc.select("div>a.nxt");
				if(next!=null&&!next.isEmpty()){
					String nextUrl = next.get(0).attr("href");
					doc = Jsoup.connect(nextUrl).get();
					System.out.println(nextUrl);
					index = selectFields(doc,sheet,style,index);
				}else{
					break;
				}
				
			}
			
			 
			/*OutputStream out = new FileOutputStream(path);
		    wb.write(out);// 写入File
		    out.flush();
		    out.close();*/
		    wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	



	public YybkRunable(String url,String name) {
		super();
		this.url = url;
		this.name = name;
	}

	public int selectFields(Document doc,XSSFSheet sheet,XSSFCellStyle style,int index){
		try {
			Elements As = doc.select("li>a.img-wrap");
			for (int i = 0; i < As.size(); i++) {
				Element element = As.get(i);
				String href = element.attr("href");
				String image = element.firstElementSibling().child(0).attr("src");
				String title = element.nextElementSibling().text();
				if(StringUtils.isEmpty(title)){
					title = "a";
				}
				
				/*Document detail  = Jsoup.connect(href).get();
				
				String desc = detail.select("div.summary>p").text();
				Elements  lables = detail.select("h2>span.label");
				Elements  dec = detail.select("p.question-dec");
				Elements items =	detail.select(".question-wrap");
				List<String> yf = new ArrayList<>();
				List<String> cf = new ArrayList<>();
				List<String> buq = new ArrayList<>();
				List<String> ye = new ArrayList<>();
				
				for (int j = 0; j < items.size(); j++) {
					Elements sameFoods =  items.get(j).select("li");
					for (Element element3 : sameFoods) {
						if(j==0){
							yf.add(element3.text());
						}else if(j==1){
							cf.add(element3.text());
						}else if(j==2){
							buq.add(element3.text());
						}else if(j==3){
							ye.add(element3.text());
						}
					}
				}
				
				try {
					writeStringToCell(sheet,index, 0,title,0,style); 
					writeStringToCell(sheet,index, 1,name,0,style); 
					writeStringToCell(sheet,index, 2,desc,0,style); 
					writeStringToCell(sheet,index, 3,lables.get(0).text(),0,style); //
					writeStringToCell(sheet,index, 4,dec.get(0).text(),0,style); //
					writeStringToCell(sheet,index, 5,lables.get(1).text().toString(),0,style); //
					writeStringToCell(sheet,index, 6,dec.get(1).text(),0,style); //
					writeStringToCell(sheet,index, 7,lables.get(2).text(),0,style); //
					writeStringToCell(sheet,index, 8,dec.get(2).text(),0,style); //
					
					try {
						writeStringToCell(sheet,index, 9,lables.get(3).text(),0,style); //
					} catch (Exception e) {
						writeStringToCell(sheet,index, 9,"",0,style); //
					}
					try {
						writeStringToCell(sheet,index, 10,dec.get(3).text(),0,style); //
					} catch (Exception e) {
						writeStringToCell(sheet,index, 10,"",0,style); //
					}
					writeStringToCell(sheet,index, 11,yf.toString(),0,style); //
					writeStringToCell(sheet,index, 12,cf.toString(),0,style); //
					writeStringToCell(sheet,index, 13,buq.toString(),0,style); //
					writeStringToCell(sheet,index, 14,ye.toString(),0,style); //
				} catch (Exception e) {
					System.out.println(title+"=获取内容错误="+e.getMessage());
				}*/
				
				try {
					//图片下载
					title = gbEncoding(title.trim());
					downloadPicture(image, title);
				} catch (Exception e) {
					System.out.println(title+"=下载图片错误="+e.getMessage());
				}
				
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
	}

	public void writeStringToCell(XSSFSheet sheet,int numRow, int numCol, Object strval,int type,CellStyle style) {
		try {
			strval = strval==null ? "" : strval;
			if (sheet != null) {
				Row row  = sheet.getRow(numRow);
				if(row==null)row = sheet.createRow(numRow);
				row.setHeight((short)500);
				Cell cell =  row.createCell(numCol);
				cell.setCellStyle(style);
				switch (type) {
				default:
					cell.setCellValue(strval==null ?"": strval.toString());
					break;
				case 1:
					cell.setCellValue(Double.parseDouble(strval.toString()));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	private static void downloadPicture(String urlList,String filename) {
        URL url = null;
        int imageNumber = 0;

        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String imageName =  "D:/cookbook/images/"+filename+".jpg";

            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context=output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	 public static String gbEncoding(final String gbString) {   //gbString = "测试"
         char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]
         String unicodeBytes = "";   
         for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {   
             String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串
               if (hexB.length() <= 2) {   
                   hexB = "00" + hexB;   
              }   
              unicodeBytes = unicodeBytes +  hexB;  
              //"\\u" +
         }   
//         System.out.println("unicodeBytes is: " + unicodeBytes);   
         return unicodeBytes;   
	 }
	

	
}
