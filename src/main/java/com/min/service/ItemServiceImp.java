package com.min.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.dao.ItemDao;
import com.min.model.Item;

@Service
public class ItemServiceImp implements ItemService{

	private ItemDao itemDao;
	
	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	@Override
	public Item findById(String id) {
		// TODO Auto-generated method stub
		Item item = itemDao.findById(id);
		return item;
	}

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		List<Item> items = itemDao.findAll();
		return items;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		itemDao.delete(id);
	}
	
	@Override
	public void save(Item item) {
		// TODO Auto-generated method stub
		itemDao.save(item);
	}
	
	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub
		itemDao.update(item);
	}

}
