package com.min.dao;

import java.util.List;

import com.min.model.Item;
import com.min.model.ItemHistory;

public interface ItemHistoryDao {

	/** 
	 * @author Min
	 * @date Aug 17, 2015
	 * @return void 
	 * @throws 
	 */
	Item findById(String id);

	List<Item> findAll();

	void save(ItemHistory result);

	void delete(String id);
}
