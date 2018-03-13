package com.lance.code.jsoup.service;

import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Detail {
	public static void main(String[] args) {
//		String url = "http://1028.by-health.com/KaiHey/user/detail?userId=";
		String url= "http://1028.by-health.com/KaiHey/group/rankingInGroup?indexPage=1&pageSize=100&groupId=";
		StringBuffer sb  = new StringBuffer();
		for (int i = 2; i <23; i++) {
			try {
				Thread.sleep(5);
				String response = Jsoup.connect(url+i)
				.header("Accept", "application/json, text/plain, */*")
				.header("Accept-Encoding", "gzip, deflate")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept", "text/*, application/xml,application/xhtml+xml")
				.header("Cookie", "Hm_lpvt_f970cc249fbc73392d6c8e526e45319c=1509074187; Hm_lvt_f970cc249fbc73392d6c8e526e45319c=1509022854,1509026452,1509065316,1509072765;")
				.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_1 like Mac OS X) AppleWebKit/604.3.5 (KHTML, like Gecko) Mobile/15B93 kkPlus/v5.2.6.R.20170620,3 ekp-i-kk5")
				.ignoreContentType(true).get().body().text();
				
				JSONObject json = (JSONObject)JSONObject.parse(response);
				String result = json.get("state").toString();
				if("true".equals(result)){
					JSONObject dataInfo = (JSONObject)JSONObject.parse(json.get("data").toString());
					JSONArray userlist = JSONArray.parseArray(dataInfo.get("datas").toString());
					for (int j = 0; j < userlist.size(); j++) {
						JSONObject userInfo = (JSONObject)JSONObject.parse(userlist.get(j).toString());
//						userInfo.get("userid");
//						System.out.println(userInfo.get("userid"));
						sb.append(userInfo.get("userid")+",");
						
						
					}
//					System.out.println(response);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(sb.toString());
		
	}
}
