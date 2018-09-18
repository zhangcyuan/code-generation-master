package com.lance.code.download.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.lance.code.generation.domain.ClArticle;
import com.lance.code.generation.mapper.TablesMapper;
import com.lance.code.generation.web.SimpleApplication;

/**
 * 
* 小草微微 递增
* @author zhangchangyuan
* @version 创建时间：2018年2月2日 上午9:50:52
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SimpleApplication.class)
public class XiaoCaoUpTest {
	
	
	@Autowired
	private TablesMapper tablesMapper;
	
	
	@Test
	public void main() {

		//http://cl.krj1.pw/htm_data/7/1503/1423153.html

		//没有找到多少次之后，重置
		int resetSize = 150;
		//年月份
		String yearAndMonth = "1503";
		//最大ID
		int minID = 1423153;
		//分类
		String url = "http://cl.krj1.pw/htm_data/7/%s/%d.html"; 
//			String url = "http://cl.krj1.pw/htm_data/7/1412/1300030.html";
		//没有找到的次数 临时变量
		int notFind = 0;
		for (int i = minID; i <9999999; i++) {
			if(notFind>=resetSize){
				i=i-notFind;
				//月份加1
				yearAndMonth = MonthDown(yearAndMonth, 1);
				notFind=0;
				System.out.println("查找了"+resetSize+"次，没有找到则月份递减，数据回归");
			}
			
			String pageUrl = String.format(url, yearAndMonth,i);
			try{
				Response response = Jsoup.connect(pageUrl)
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//					.header("Accept-Encoding", "gzip, deflate, br")
				.header("accept-language", "zh-CN,zh;q=0.9")
//					.header("pragma", "no-cache")
//					.header("Content-Type", "application/x-www-form-urlencoded")
//					.header("Content-Type", "application/json;charset=gb2312")
//					.header("Cookie", "user-key=29281e16-c895-4f44-a4b4-7dc4f8364196; dmpjs=dmp-d37659538075886bc9686c0c5bed0d01d68f1c2; mt_xid=V2_52007VwMaV1tZV1sZTilZAGRREgFeXk4KFklJQAA0VkZOVVgBXANKHgwEYFEaBlkLUQgvShhcA3sDEk5cWENaHkIYXQ5nACJQbVpiWR5PEVkAVwMbVV0%3D; warehistory=\"17216928653,1312979793,1312979789,3538994,1039821,2457897,1044680,4663011,4201838,2327501,\"; m_uuid_new=4CF3F5097D4242688C73D12BE8B4D707; M_Identification=f982fc0dd8d8e2a5_9dcc896683c62c04464c749c59475554; M_Identification_abtest=20171101022041247_14058081; abtest=20171101102044862_11; TrackID=1oauWc3O8cuJIOG59xzH5EuPycQk4Hg6xGimlNSN_PpoB-EDPcmXlJrzeAGePC5fKHDAUUdrwtGxwq36HQfY1TmPiUFn2TYNSg2TVLyL1-sU; pin=jd_66d61ff184a0e; unick=mr_changyuan; _tp=10NXdLqLQ6GJ6nQhqOIXkdlqpXkWrahRsAZKD8bZ7aE%3D; _pst=jd_66d61ff184a0e; ceshi3.com=201; unpl=V2_ZzNtbUtRQhJ8DhYELkoLUmIEEV5LA0EdIA9OAXIQWQcyA0BVclRCFXMURlRnGFwUZwIZWUNcQhRFCEdkexhdBGYCEVVBVHMldDhFVEsZXTVnCxdbQ1JCE3I4dlNLKYiE2dqp25r8%2fcH7unZVSxhsAGcKF11BVEUcRUMoVTYZVABhAhdcRFBzFEUL; ipLocation=%u5317%u4EAC; areaId=19; ipLoc-djd=19-1601-3633-0.2147483645; cn=29; _rdCube=%7B%22p220834%22%3A%22%2C2971050%2C2002550%22%7D; _jrda=1; mobilev=html5; autoOpenApp_downCloseDate_auto=1509585301628_1800000; downloadAppPlugIn_downCloseDate=1509585320809_86400000; shshshfpa=e7613719-5931-a0e8-6008-20c3e4127b5f-1509585327; TrackerID=Q_Ca21lafDtwx6ah-UkNo1CAcH4z8AkcAsezjGzi73nZc2D_heXeGRz8GXVQF0Oq47GkZi4RfP-iZnAJFrdizxpy4AJ_74IbzK0Aaq0OPGE; pinId=Cu06a1N_bFAWd6cpVrDFZbV9-x-f3wj7; pt_key=AAFZ-nHaADCcy3WyUIvioP2gLBjzlSBesyZF9UI_oe_4XmDaf4H6SmQo-yUEjz3kTPxOi7QFqI4; pt_pin=jd_66d61ff184a0e; pt_token=jcl5ajox; pwdt_id=jd_66d61ff184a0e; whwswswws=OXrk5U%2F2Ex7ArKtDb7qdbYy%2FkE4uZ5UwDt9hp0s9pKYiybC6shYE7Q%3D%3D; retina=0; cid=9; webp=1; __wga=1509585373910.1509585373910.1509585373910.1509585373910.1.1; PPRD_P=UUID.194704076; sc_width=1366; wq_area=19_1601_36953%7C3; shshshfp=0fedc19a15dd194b511cfae918593c0a; shshshsID=5c0afafc9d1e6f2aa937c1d0c2a1c556_2_1509585374073; shshshfpb=27dd76f585f494f37bb02f962fac6690952c1d2ea510df3cae99a62450; 3AB9D23F7A4B3C9B=65C6ZW2MYSMK2IWEPPGJKTSQTB5MK3ARGYY6Z6HQAJ3V625E53ZHVPG5G6FC4R55A3KSIVUTNITOGXBC4CKISYNRAA; USER_FLAG_CHECK=bb5bf148d89203e3e8ba72da72f8dbb9; __jdv=122270672|baidu|-|organic|not set|1509586482601; thor=C12E96D1C491F32D898BC09CB0101027C41F44EE31D028306A541318977A38E9456622F20DCBF2C4BCBF7DEAC97076BF0C89E38EF96422DFAA1E369A45FB057087B57F58194AF7687BD14AC653EDDC93FC238A8B1EBD9F490E542D798A20532A923BC4D8DAC958CB39E817080B422C390E73077D7885A92D34886E02672B7788DBCC95D917157C79746CC98B980534F17F3D89BBA1B021DC5DD26EC6A50D12D6; __jda=122270672.194704076.1470711211.1509586274.1509586483.121; __jdb=122270672.3.194704076|121.1509586483; __jdc=122270672; __jdu=194704076; sid=d9a7847353ed06b17e43856e66553403; mba_muid=194704076; mba_sid=15095853012494088882768552939.13")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36")
//					.header("referer", "https://pro.m.jd.com/mall/active/ggTAyJ5g3o8QQdD7CAwM4m2siZ8/index.html?has_native=0&cu=true&utm_source=url.cn&utm_medium=tuiguang&utm_campaign=t_2009631446_&utm_term=6a05032ec4a845d3a7e78f259a46dd25&abt=3&sid=d9a7847353ed06b17e43856e66553403")
				.ignoreContentType(true).execute();
				System.out.println(pageUrl);
				//查找到后，次数归0,重新开始
				notFind=0;
				Document document = response.charset("gbk").parse();
				String title = document.head().select("title").text().replaceAll(" 草榴社區  - powered by phpwind.net", "");
				String posttime = document.body().select("div.tipad").first().ownText().substring(7, 23);
				Elements inputTextElements = document.body().select("input[type=text]");
				Element maxPageElement = inputTextElements.get(inputTextElements.size()-2);
				String maxPage = "0";
				if(StringUtils.isEmpty(maxPageElement.val())){
					maxPage = maxPageElement.attr("size");
				}else{
					maxPage =maxPageElement.val();
				}
//					System.out.println(maxPage);
//					System.out.println(title);
//					System.out.println(posttime);
				ClArticle article = new ClArticle();
				article.setCode(i+"");
				article.setPosttime(posttime);
				article.setUrl(pageUrl);
				article.setTitle(title);
				article.setHot(maxPage);
				article.setCreatetime(new Date());
				article.setContext(document.toString());
				tablesMapper.insertClArticle(article);
//					System.out.println(document.body());
				Thread.sleep(5);
//					System.out.println(response);
//					Gson gson = new Gson();	
//					Map<String,Object> map = gson.fromJson(response, Map.class);
//					Map<String, Object> data = (Map)map.get("data");
//					List<Map<String,Object>> list = (List) data.get("list");
			}catch(HttpStatusException he){
				notFind++;
				System.out.println( String.format("%d没有找到月份：%s---序号:%d---%s", notFind,yearAndMonth,i,pageUrl));
				continue;
			}catch (Exception e) {
				e.printStackTrace();
			}
//				System.out.println("last size = "+counts);
		}
			
	}

	
	public static String MonthDown(String date,int num){
		DateFormat df = new SimpleDateFormat("yyMM");  
        try {  
            Date d1 = df.parse(date);  
            Calendar  g = Calendar.getInstance();  
            g.setTime(d1);  
            g.add(Calendar.MONTH,num);             
            Date d2 = g.getTime();  
            return df.format(d2);
        } catch (ParseException e) {              
            e.printStackTrace();  
        }  
        return null;
	}
		
		
	
	
	
}
