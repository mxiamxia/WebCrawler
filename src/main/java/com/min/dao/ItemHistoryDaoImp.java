package com.min.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.min.model.Item;
import com.min.model.ItemHistory;

@Repository
public class ItemHistoryDaoImp extends JdbcDaoSupport implements ItemHistoryDao{

	/** 
	 * @author Min
	 * @date Aug 17, 2015
	 * @return void 
	 * @throws 
	 */
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
		String sql = "INSERT INTO item_history (item_id, dtime, original_price, sale_price, size, color, recommendation) VALUES (?, ?, ?, ?, ?, ?, ?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { result.getItem_id(), result.getDtime(), result.getOriginal_price(),result.getSale_price(),
						result.getSize(), result.getColor(), result.isRecommendation() });
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
