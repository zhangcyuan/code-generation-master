package com.lance.code.jsoup.service;

import java.util.Map;

import org.jsoup.Jsoup;
import org.junit.Test;

import com.lance.code.generation.utils.ReadExcelUtil;

public class PostLxData {

	@Test
	public void test() {
		//http://lxpd.api.nutri-star.com/?userId=16785105&recordKey=3F761CE37EB98565011FFA5AF16A4843F6EA73761DE24751#t-report/
		String filePath = "d:/cookbook/乐心数据_20171110.xlsx";
		String url = "http://lxpd.api.nutri-star.com/api/sportnutrient/sportManageLX/saveSportData.do?";
		ReadExcelUtil excelReader = new ReadExcelUtil(filePath);
		Map<Integer, Map<Integer, Object>> map;
		try {
			map = excelReader.readExcelContent();
//			System.out.println("获得Excel表格的内容总共："+map.size()+"条记录：");
			for (int i = 1; i <= map.size(); i++) {
				try {
					Map<Integer, Object> cellValue = map.get(i);
					String params =  cellValue.get(0).toString().trim();
					String realyURL = (url+params).replaceFirst("appId=lx_sport2017_app&", "");
					System.out.println(realyURL);
					String response = Jsoup.connect(realyURL)
//							.header("Accept", "application/json, text/plain, */*")
//							.header("Accept-Encoding", "gzip, deflate")
//							.header("Content-Type", "application/x-www-form-urlencoded")
//							.header("Accept", "text/*, application/xml,application/xhtml+xml")
//							.header("Cookie", "Hm_lpvt_f970cc249fbc73392d6c8e526e45319c=1509074187; Hm_lvt_f970cc249fbc73392d6c8e526e45319c=1509022854,1509026452,1509065316,1509072765;")
//							.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_1 like Mac OS X) AppleWebKit/604.3.5 (KHTML, like Gecko) Mobile/15B93 kkPlus/v5.2.6.R.20170620,3 ekp-i-kk5")
							.ignoreContentType(true).post().body().text();
							System.out.println(response);
					
					System.out.println(params);
					Thread.sleep(1000);
				} catch (Exception e) {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
