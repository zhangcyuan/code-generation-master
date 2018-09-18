package com.lance.code.thread.service;
/**
*
* @author zhangchangyuan
* @version 创建时间：2018年7月10日 上午11:44:16
*/
public class YybkMain {
	
	public static void main(String[] args) {
		
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20022_s0_e0_p1","五谷杂粮")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20023_s0_e0_p1","食物加工篇")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20024_s0_e0_p1","蔬菜_食用菌")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20025_s0_e0_p1","肉禽蛋_野味")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20026_s0_e0_p1","水果")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20027_s0_e0_p1","水产品_海鲜")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20028_s0_e0_p1","调味品")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20029_s0_e0_p1","饮品_饮料")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20030_s0_e0_p1","零食_小吃")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20031_s0_e0_p1","豆_乳_奶制品")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20032_s0_e0_p1","干果")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20033_s0_e0_p1","补品")).start();
		new Thread(new YybkRunable("http://www.mama.cn/z/t20021/f20034_s0_e0_p1","草药")).start();
		
	}
	
	
}
