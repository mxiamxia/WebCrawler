package com.min.service;

import java.util.List;

import com.min.model.Item;


public interface ItemService {
	Item findById(String id);

	List<Item> findAll();

	void save(Item item);

	void update(Item item);

	void delete(String id);
}
