package com.min.dao;

import java.util.List;

import com.min.model.Item;


public interface ItemDao {
	Item findById(String id);

	List<Item> findAll();

	void save(Item item);

	void update(Item item);

	void delete(String id);

}
