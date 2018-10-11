package com.lance.code.download.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lance.code.generation.utils.DownLoadImgaeUtil;
import com.lance.code.generation.utils.TxtUtil;

/**
* AISS图片APP图片下载
* @author zhangchangyuan
* @version 创建时间：2018年2月2日 上午9:50:52
*/
public class AISIDownLoadTest {
	static String txtPath = "E:/HaimaApp/aisipic/readme.txt";
	//图片下载路径
	static String filePath ="E:/HaimaApp/aisipic/";
	
	static String image_host = "http://aiss.obs.cn-north-1.myhwclouds.com/";
	
	public static void main(String[] args) {
		//分类
		String url = "http://api.pmkoo.cn/aiss/suite/sourceList.do?page=1"; 
		//每个分类中的列表
		String url2 = "http://api.pmkoo.cn/aiss/suite/suiteList.do?page=%s&sourceId=%s";
		
		
		//图片链接 
		String imageUrl = image_host+"picture/";
		//总分页
		int pageNum = 50;
		//图片总数
		int counts = 0;
		
		//txt文件路径
		String txtContext = TxtUtil.readTxt(txtPath);
		//各分类的进度
		Map<String, Object> catalogMap = (Map<String, Object>)JSONObject.parse(txtContext);
		
		try{
			String response = Jsoup.connect(url)
//			.header("Accept", "*/*")
//			.header("Accept-Encoding", "gzip, deflate, br")
//			.header("accept-language", "zh-CN,zh;q=0.9")
//			.header("pragma", "no-cache")
//			.header("Content-Type", "application/x-www-form-urlencoded")
			.header("Content-Type", "application/json;charset=UTF-8")
//			.header("Cookie", "user-key=29281e16-c895-4f44-a4b4-7dc4f8364196; dmpjs=dmp-d37659538075886bc9686c0c5bed0d01d68f1c2; mt_xid=V2_52007VwMaV1tZV1sZTilZAGRREgFeXk4KFklJQAA0VkZOVVgBXANKHgwEYFEaBlkLUQgvShhcA3sDEk5cWENaHkIYXQ5nACJQbVpiWR5PEVkAVwMbVV0%3D; warehistory=\"17216928653,1312979793,1312979789,3538994,1039821,2457897,1044680,4663011,4201838,2327501,\"; m_uuid_new=4CF3F5097D4242688C73D12BE8B4D707; M_Identification=f982fc0dd8d8e2a5_9dcc896683c62c04464c749c59475554; M_Identification_abtest=20171101022041247_14058081; abtest=20171101102044862_11; TrackID=1oauWc3O8cuJIOG59xzH5EuPycQk4Hg6xGimlNSN_PpoB-EDPcmXlJrzeAGePC5fKHDAUUdrwtGxwq36HQfY1TmPiUFn2TYNSg2TVLyL1-sU; pin=jd_66d61ff184a0e; unick=mr_changyuan; _tp=10NXdLqLQ6GJ6nQhqOIXkdlqpXkWrahRsAZKD8bZ7aE%3D; _pst=jd_66d61ff184a0e; ceshi3.com=201; unpl=V2_ZzNtbUtRQhJ8DhYELkoLUmIEEV5LA0EdIA9OAXIQWQcyA0BVclRCFXMURlRnGFwUZwIZWUNcQhRFCEdkexhdBGYCEVVBVHMldDhFVEsZXTVnCxdbQ1JCE3I4dlNLKYiE2dqp25r8%2fcH7unZVSxhsAGcKF11BVEUcRUMoVTYZVABhAhdcRFBzFEUL; ipLocation=%u5317%u4EAC; areaId=19; ipLoc-djd=19-1601-3633-0.2147483645; cn=29; _rdCube=%7B%22p220834%22%3A%22%2C2971050%2C2002550%22%7D; _jrda=1; mobilev=html5; autoOpenApp_downCloseDate_auto=1509585301628_1800000; downloadAppPlugIn_downCloseDate=1509585320809_86400000; shshshfpa=e7613719-5931-a0e8-6008-20c3e4127b5f-1509585327; TrackerID=Q_Ca21lafDtwx6ah-UkNo1CAcH4z8AkcAsezjGzi73nZc2D_heXeGRz8GXVQF0Oq47GkZi4RfP-iZnAJFrdizxpy4AJ_74IbzK0Aaq0OPGE; pinId=Cu06a1N_bFAWd6cpVrDFZbV9-x-f3wj7; pt_key=AAFZ-nHaADCcy3WyUIvioP2gLBjzlSBesyZF9UI_oe_4XmDaf4H6SmQo-yUEjz3kTPxOi7QFqI4; pt_pin=jd_66d61ff184a0e; pt_token=jcl5ajox; pwdt_id=jd_66d61ff184a0e; whwswswws=OXrk5U%2F2Ex7ArKtDb7qdbYy%2FkE4uZ5UwDt9hp0s9pKYiybC6shYE7Q%3D%3D; retina=0; cid=9; webp=1; __wga=1509585373910.1509585373910.1509585373910.1509585373910.1.1; PPRD_P=UUID.194704076; sc_width=1366; wq_area=19_1601_36953%7C3; shshshfp=0fedc19a15dd194b511cfae918593c0a; shshshsID=5c0afafc9d1e6f2aa937c1d0c2a1c556_2_1509585374073; shshshfpb=27dd76f585f494f37bb02f962fac6690952c1d2ea510df3cae99a62450; 3AB9D23F7A4B3C9B=65C6ZW2MYSMK2IWEPPGJKTSQTB5MK3ARGYY6Z6HQAJ3V625E53ZHVPG5G6FC4R55A3KSIVUTNITOGXBC4CKISYNRAA; USER_FLAG_CHECK=bb5bf148d89203e3e8ba72da72f8dbb9; __jdv=122270672|baidu|-|organic|not set|1509586482601; thor=C12E96D1C491F32D898BC09CB0101027C41F44EE31D028306A541318977A38E9456622F20DCBF2C4BCBF7DEAC97076BF0C89E38EF96422DFAA1E369A45FB057087B57F58194AF7687BD14AC653EDDC93FC238A8B1EBD9F490E542D798A20532A923BC4D8DAC958CB39E817080B422C390E73077D7885A92D34886E02672B7788DBCC95D917157C79746CC98B980534F17F3D89BBA1B021DC5DD26EC6A50D12D6; __jda=122270672.194704076.1470711211.1509586274.1509586483.121; __jdb=122270672.3.194704076|121.1509586483; __jdc=122270672; __jdu=194704076; sid=d9a7847353ed06b17e43856e66553403; mba_muid=194704076; mba_sid=15095853012494088882768552939.13")
//			.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36")
//			.header("referer", "https://pro.m.jd.com/mall/active/ggTAyJ5g3o8QQdD7CAwM4m2siZ8/index.html?has_native=0&cu=true&utm_source=url.cn&utm_medium=tuiguang&utm_campaign=t_2009631446_&utm_term=6a05032ec4a845d3a7e78f259a46dd25&abt=3&sid=d9a7847353ed06b17e43856e66553403")
			.ignoreContentType(true).post().body().text();
//			System.out.println(response);
			Gson gson = new Gson();	
			Map<String,Object> map = gson.fromJson(response, Map.class);
			Map<String, Object> data = (Map)map.get("data");
			List<Map<String,Object>> list = (List) data.get("list");
			for (Map<String, Object> image : list) {
				String catalog = image.get("catalog").toString();
				int lastId = Integer.parseInt(catalogMap.get(catalog).toString());
				boolean needBreak = false;
				for (int j = 1; j < pageNum; j++) {
					if(needBreak){
						break;
					}
					String pageUrl = String.format(url2, j,Double.valueOf(image.get("id").toString()).intValue());
					try {
						String listBody = Jsoup.connect(pageUrl)
								.header("Content-Type", "application/json;charset=UTF-8").ignoreContentType(true).post().body().text();
						//http://tuigirl-1254818389.cosbj.myqcloud.com/picture/playboy/286/0.jpg
						Map<String,Object> map2 = gson.fromJson(listBody, Map.class);
						System.out.println(map2);
						Map<String, Object> m2Data = (Map)map2.get("data");
						List<Map<String,Object>> list2 = (List) m2Data.get("list");
						int newLastId = lastId;
						for (int k = 0; k < list2.size(); k++) {
							Map<String, Object> pic = list2.get(k);
							//System.out.println("\n\t"+pic);
							//编号
							int issue =  Double.valueOf(pic.get("issue").toString()).intValue();
							if(k==0&&issue>newLastId){
								newLastId = issue;
							}
							if(issue<=lastId){
								needBreak = true;
								//将最新的数据放进去
								catalogMap.put(catalog,newLastId);
								TxtUtil.writeTxt(txtPath, catalogMap.toString());
								break;
							}
							 //图片数量
							int imageCount = Double.valueOf(pic.get("pictureCount").toString()).intValue();
							for (int i = 0; i < imageCount; i++) {
								counts++;
								String lastUrl= imageUrl+catalog+"/"+issue+"/"+i+".jpg";
								System.out.println(lastUrl);
								//downImages(filePath, lastUrl);
								DownLoadImgaeUtil.downImages(filePath, lastUrl, image_host,null,null);
								Thread.sleep(20);
							}
						}
					} catch (Exception e) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("last size = "+counts);
	}
	
	
	/*public static void downImages(String filePath,String imgUrl) throws UnsupportedEncodingException {  
		//http://tuigirl-1254818389.cosbj.myqcloud.com/picture/playboy/286/0.jpg
		//E:/HaimaApp/aisipic/playboy/286/0.jpg
        //图片url中的前面部分：例如"http://images.csdn.net/"  
		
		String lastFilePath = imgUrl.replace(image_host, filePath);
		String dirs = lastFilePath.substring(0, lastFilePath.lastIndexOf("/"));
        try {  
            //创建文件目录  
            File files = new File(dirs);  
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
    }  */
	
	
	
	
}
