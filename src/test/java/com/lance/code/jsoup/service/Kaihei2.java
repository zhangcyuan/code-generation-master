package com.lance.code.jsoup.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;

public class Kaihei2 {
	public static void main(String[] args) {
//		String url ="http://1028.by-health.com/KaiHey/lottery/drawALottery?userId=5933";
//		String url = "http://1028.by-health.com/KaiHey/user/battle?attackUserId=609&beAttackUserId=4126";
		String url = "http://1028.by-health.com/KaiHey/user/rankingInServer?indexPage=1&pageSize=1300";
		while(true){
			try {
				
				String response = Jsoup.connect(url)
				.header("Accept", "application/json, text/plain, */*")
				.header("Accept-Encoding", "gzip, deflate")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept", "text/*, application/xml,application/xhtml+xml")
				.header("Cookie", "Hm_lpvt_f970cc249fbc73392d6c8e526e45319c=1509106345; Hm_lvt_f970cc249fbc73392d6c8e526e45319c=1509072765,1509086623,1509096136,1509103545; LtpaToken=AAECAzU5RjMyMjBBNTlGM0NBQ0F6aGFuZ2N5QGJ5LWhlYWx0aGRjLmNvbWcxMqa1OUAk5ZoatfiE0ggC6Dcq")
				.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_1 like Mac OS X) AppleWebKit/604.3.5 (KHTML, like Gecko) Mobile/15B93 kkPlus/v5.2.6.R.20170620,3 ekp-i-kk5")
				.ignoreContentType(true).get().body().text();
				System.out.println(response);
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
