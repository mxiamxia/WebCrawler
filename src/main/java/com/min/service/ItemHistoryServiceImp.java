package com.min.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.dao.ItemDao;
import com.min.dao.ItemHistoryDao;
import com.min.model.Item;
import com.min.model.ItemHistory;

@Service
public class ItemHistoryServiceImp implements ItemHistoryService{

	/** 
	 * @author Min
	 * @date Aug 18, 2015
	 * @return void 
	 * @throws 
	 */
	
	private ItemHistoryDao itemHistoryDao;
	
	@Autowired
	public void setItemHistoryDao(ItemHistoryDao itemHistoryDao) {
		this.itemHistoryDao = itemHistoryDao;
	}

	@Override
	public Item findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ItemHistory result) {
		// TODO Auto-generated method stub
		itemHistoryDao.save(result);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
