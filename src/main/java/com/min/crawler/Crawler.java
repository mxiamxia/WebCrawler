//package com.min.crawler;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.RejectedExecutionHandler;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.pool.ObjectPool;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.min.email.ConfigProps;
//import com.min.executor.HttpExecutor;
//import com.min.log.Log;
//import com.min.model.Page;
//import com.min.model.Result;
//import com.min.pool.ExecutorPool;
//import com.min.schedule.ScanScheduler;
//import com.min.utils.PropertiesLoader;
//
//public class Crawler 
//{
//	
//	public static List<Page> list = new ArrayList<Page>();
////	public static List rstList = Collections.synchronizedList(new ArrayList<Result>());
//	@Autowired
//	private ScanScheduler scheduler;
//	public void setScheduler(ScanScheduler scheduler) {
//		this.scheduler = scheduler;
//	}
//
//	public static void init()
//	{
//		//init properties
//		ConfigProps.props = PropertiesLoader.load();
//		if(!ConfigProps.props.isEmpty()){
//			initList(ConfigProps.props);
//		}
//		Log.info("Loaded Properties");
//		//setup scheduler
//		ScanScheduler sch = new ScanScheduler(ConfigProps.props);
//		sch.run();
//		
////		try {
////			Thread.sleep(100000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		sch.stop();
////		try {
////			Thread.sleep(20000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		System.out.println("Start task again");
////		sch.run();
//	}
//	
//	private static List<Page> initList(Properties props) {
//		// TODO Auto-generated method stub
//		if(props.isEmpty())
//			return null;
//		String[] items = props.getProperty("item").split(";");
//		for(String item : items)
//		{
//			Page p = new Page();
//			String[] item_detail = item.split(",");
//			p.setItem(item_detail[0]);
//			p.setBuy_price(Double.parseDouble(item_detail[1]));
//			p.setURL(props.getProperty("httpUrl"));
//			list.add(p);
//		}
//		return list;
//	}
////
////	public void cleanRst()
////	{
////		Crawler.rstList.clear();
////	}
////	public List getRstList() {
////		return rstList;
////	}
////
////	public void setRstList(List rstList) {
////		this.rstList = rstList;
////	}
//}
