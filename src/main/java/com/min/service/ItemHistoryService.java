package com.min.service;

import java.util.List;

import com.min.model.Item;
import com.min.model.ItemHistory;

public interface ItemHistoryService {

	/** 
	 * @author Min
	 * @date Aug 18, 2015
	 * @return void 
	 * @throws 
	 */
	
	Item findById(String id);

	List<Item> findAll();

	void save(ItemHistory result);

	void delete(String id);

}
