package com.min.executor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.log.Log;
import com.min.model.Item;
import com.min.model.ItemHistory;
import com.min.queue.QueueUtils;

public class HttpExecutor{
	String p_url = "https://factory.jcrew.com/search2/index.jsp";
	
//	@Autowired
//	private	QueueUtils queue;
//	public void setQueue(QueueUtils queue) {
//		this.queue = queue;
//	}
	
	public void run(Item item, ArrayBlockingQueue<ItemHistory> respQ) {
		List<ItemHistory> list = new ArrayList<ItemHistory>();
		try	{
			String params = "?N=0&Nloc=&Ntrm=%s&Npge=1&Nsrt=0";
			params = String.format(params, item.getItem_id());
			String url = p_url + params;
			String resp = getPageContent(url);
			System.out.println(resp);
			process(item, resp, list);
			if(list.size() == 0)
			{
//				String url2 = direct_url(resp);
//				String resp2 = getPageContent(url2);
//				Log.rout_info(resp2);
//				process_2(resp2);
				process_1(item, resp, list);
			}
		}
		catch(Exception e)
		{
			Log.info(e.getMessage());
			return;
		}
		
		if(list.size() > 0) {
			System.out.println(respQ + " HttpExecutor");
			for(ItemHistory rst : list) {
				respQ.add(rst);
			}
			System.out.println(respQ.size() + " HttpExecutor");
		}
		//send email
//		if(needNotify)
//		{
//			SendEmail.sendAttachmentMail(rst, ConfigProps.props);
//		}
	}
	
	private void process_1(Item item, String resp, List<ItemHistory> list) {
		// TODO Auto-generated method stub
		double salePrice;
		int beg = resp.indexOf("ProductValueLocal");
		int end = resp.indexOf(");", beg);
		String value = resp.substring(beg+20, end-1);
		try {
			salePrice  = Double.parseDouble(value);
			ItemHistory item_rst = new ItemHistory();
			item_rst.setItem_id(item.getItem_id());
			item_rst.setSale_price(salePrice);
			if(salePrice <= item.getSale_price()) {
				item_rst.setRecommendation(true);
			}
			Timestamp tt=new Timestamp(System.currentTimeMillis());
			item_rst.setDtime(tt);
			list.add(item_rst);
		}
		catch(Exception e) {
			Log.info(e.getMessage());
		}
	}

	private String direct_url(String resp) {
		// TODO Auto-generated method stub
		int _beg = resp.indexOf("canonicalURL");
		int _end = resp.indexOf(",", _beg);
		String url = resp.substring(_beg+16, _end-1);
		Log.info(url, Log.ROUT);
		return url;
	}

	private String getPageContent(String url) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		CloseableHttpClient client = HttpClients.createDefault();
		try
		{
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			// Get the response
			BufferedReader rd = new BufferedReader
					(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			} 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				client.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public void process(Item item, String resp, List<ItemHistory> list)
	{
		double salePrice = 0;
		double strikedPrice = 0;
		int idx = -1;
		String regex = "product-description-line product-price-was";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(resp);
		try {
			while(m.find())	{
				ItemHistory item_rst = new ItemHistory();
				idx = m.start();
				int beg = resp.indexOf("<", idx);
				int color_beg = resp.indexOf("color_name", beg);
				int color_end = resp.indexOf("&", color_beg);
				String color = resp.substring(color_beg+11, color_end);
				int orig_beg = resp.indexOf("product-list-price-striked notranslate", beg);
				int orig_end = resp.indexOf("<", orig_beg);
				String orig_price = resp.substring(orig_beg+43, orig_end);
				if(orig_price != null) {
					strikedPrice = Double.parseDouble(orig_price);
				}
				item_rst.setItem_id(item.getItem_id());
				item_rst.setOriginal_price(strikedPrice);
				item_rst.setColor(color);
				Timestamp tt=new Timestamp(System.currentTimeMillis());
				item_rst.setDtime(tt);
				list.add(item_rst);
			}
		}
		catch(Exception e) {
			Log.info(e.getMessage());
		}
		regex = "product-description-line product-price-now";
		p = Pattern.compile(regex);
		m = p.matcher(resp);
		int i=0;
		try {
			while(m.find())	{
				idx = m.start();
				int beg = resp.indexOf("<", idx);
				int sale_beg = resp.indexOf("product-sale-price notranslate", beg);
				int sale_end = resp.indexOf("<", sale_beg);
				String sale_price = resp.substring(sale_beg+35, sale_end);
				if(sale_price != null) {
					salePrice = Double.parseDouble(sale_price);
					if(salePrice <= item.getSale_price()) {
						list.get(i).setRecommendation(true);
					}
				}
				list.get(i).setSale_price(salePrice);
				i++;
				Log.info("Item# " + item.getItem_id() +" : " + strikedPrice + "----" + salePrice, Log.ROUT);
			}
		}
		catch(Exception e) {
			Log.info(e.getMessage());
		}
	}
	
	public boolean process_2(String resp)
	{
//		int beg = resp.indexOf("\"search_topnav\" action=\"");
//		int end = resp.indexOf(">", beg);
//		String url = resp.substring(beg+24, end-1);
//		Log.rout_info(url);
		boolean gotit = false;
		int idx = -1;
		String regex = "ProductValueLocal";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(resp);
		
		while(m.find())
		{
			idx = m.start();
			int striked_end = resp.indexOf("<", idx);
			String striked_value = resp.substring(idx+28, striked_end);
			int sale_beg = resp.indexOf("product-sale-price", idx);
			int sale_end = resp.indexOf("<", sale_beg);
			String sale_value = resp.substring(sale_beg+20, sale_end);
			Log.info(striked_value + "----" + sale_value, Log.ROUT);
			gotit = true;
		}
		return gotit;
	}
	
	public static void main(String[] args) {
		Item item = new Item();
		item.setItem_id("C8588");
		HttpExecutor exe = new HttpExecutor();
//		exe.run(item);
	}
}
