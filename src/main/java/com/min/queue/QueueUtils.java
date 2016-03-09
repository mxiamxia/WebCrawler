package com.min.queue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.min.model.Item;
import com.min.model.ItemHistory;
import com.min.service.ItemService;

public class QueueUtils {

	/** 
	 * @author Min
	 * @date Aug 13, 2015
	 * @return void 
	 * @throws 
	 */
	private ArrayBlockingQueue<Item> requestQueue = new ArrayBlockingQueue<Item>(1024);
	private ArrayBlockingQueue<ItemHistory> responseQueue = new ArrayBlockingQueue<ItemHistory>(1024);
	
	@Autowired
	ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@PostConstruct
	private void initQueue() {
		List<Item> list = itemService.findAll();
		this.requestQueue.addAll(list);
	}
	public ArrayBlockingQueue<Item> getRequestQueue() {
		return requestQueue;
	}
	public void setRequestQueue(ArrayBlockingQueue<Item> requestQueue) {
		this.requestQueue = requestQueue;
	}
	public ArrayBlockingQueue<ItemHistory> getResponseQueue() {
		return responseQueue;
	}
	public void setResponseQueue(ArrayBlockingQueue<ItemHistory> responseQueue) {
		this.responseQueue = responseQueue;
	}
	
}
