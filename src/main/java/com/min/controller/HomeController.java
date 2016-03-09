package com.min.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;

import com.min.log.Log;
import com.min.model.Item;
import com.min.model.ItemHistory;
import com.min.queue.QueueUtils;
import com.min.service.ItemHistoryService;
import com.min.service.ItemService;

@Controller
public class HomeController {

	@Autowired 
	private SimpMessagingTemplate template;  
	public void setTemplate(SimpMessagingTemplate template) {
		this.template = template;
	}
	private TaskScheduler scheduler = new ConcurrentTaskScheduler();
	
	@Autowired
	ItemService itemService;

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@Autowired 
	ItemHistoryService itemHistoryService;
	public void setItemHistoryService(ItemHistoryService itemHistoryService) {
		this.itemHistoryService = itemHistoryService;
	}
	
	@Autowired
	private	QueueUtils queue;
	public void setQueue(QueueUtils queue) {
		this.queue = queue;
	}
	
	/**
	 * Iterates Item list, update the price by randomly choosing a positive
	 * or negative percentage, then broadcast it to all subscribing clients
	 */
	private void updatePriceAndBroadcast() {
		ArrayBlockingQueue<ItemHistory> que = queue.getResponseQueue();
		System.out.println(queue + " HomeController");
		System.out.println(queue.getResponseQueue() + " HomeController");
		System.out.println(queue.getResponseQueue().size() + " HomeController");
		while (que.size() > 0) {
			try {
				ItemHistory result = que.take();
				itemHistoryService.save(result);
				template.convertAndSend("/topic/price", result);
			} catch (MessagingException | InterruptedException e) {
				Log.info(e.getMessage());
			}
		}
	}

	/**
	 * Invoked after bean creation is complete, this method will schedule 
	 * updatePriceAndBroacast every 1 second
	 */
	@PostConstruct
	private void broadcastTimePeriodically() {
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override public void run() {
				updatePriceAndBroadcast();
			}
		}, 10000);
	}

	/**
	 * Handler to add one Item
	 */
	@MessageMapping("/query")
	public void addItem(Item item) throws Exception {
//		ItemPrices.add(Item);
		Item it = itemService.findById(item.getItem_id());
		it.setStatus(true);
		itemService.update(it);
		refreshQueue();
		
	}

	/**
	 * Handler to remove all Items
	 */
	@MessageMapping("/stop")
	public void removeAllItems(Item item) {
//		ItemPrices.clear();
		Item it = itemService.findById(item.getItem_id());
		it.setStatus(false);
		itemService.update(it);
		refreshQueue();
	}
	
	
	private void refreshQueue() {
		List<Item> list = new ArrayList<Item>();
		list = itemService.findAll();
		this.queue.getRequestQueue().clear();
		this.queue.getRequestQueue().addAll(list);
	}

}
