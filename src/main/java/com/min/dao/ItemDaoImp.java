package com.min.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.min.log.Log;
import com.min.model.Item;

@Repository
public class ItemDaoImp extends JdbcDaoSupport implements ItemDao{

//	@Autowired
//	private DataSource dataSource;
// 
//	@PostConstruct
//	private void initialize() {
//		setDataSource(dataSource);
//	}
	@Override
	public Item findById(String item_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM items where item_id = ?";
		Item item = null;
		try {
			item = (Item) getJdbcTemplate().queryForObject(
					sql, new Object[] { item_id }, 
					new BeanPropertyRowMapper<Item>(Item.class));
		}
		catch (Exception e) {
			Log.info(e.getMessage());
		}
		return item;
	}

	/*findAll method without JdbcDaoSupport*/
//	@Override
//	public List<Item> findAll() {
//		List<Item> items = new ArrayList();
//		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
//		 
//		Connection conn = null;
// 
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, custId);
//			Customer customer = null;
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				customer = new Customer(
//					rs.getInt("CUST_ID"),
//					rs.getString("NAME"), 
//					rs.getInt("Age")
//				);
//			}
//			rs.close();
//			ps.close();
//			return customer;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (conn != null) {
//				try {
//				conn.close();
//				} catch (SQLException e) {}
//			}
//		}
//	}
//		Item item = new Item();
//		item.setItem_id("B12345");
//		item.setSale_price(50.55);
//		item.setColor("RED");
//		item.setSize(8);
//		items.add(item);
//		return items;
//	}
	
	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM items";
		List<Item> items = new ArrayList<Item>();
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		items = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Item>(Item.class));
//		for (Map row : rows) {
//			Item item = new Item();
//			item.setItem_id((String)row.get("item_id"));
//			item.setSale_price((Double)(row.get("sale_price")));
//			item.setSize((Integer)row.get("size"));
//			item.setColor((String)row.get("color"));
//			item.setStatus((Boolean)row.get("status"));
//			items.add(item);
//		}
		return items;
	}
	
	//query single row with RowMapper
//		public Customer findByCustomerId(int custId){
//			 
//			String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
//	 
//			Customer customer = (Customer)getJdbcTemplate().queryForObject(
//					sql, new Object[] { custId }, new CustomerRowMapper());
//		
//			return customer;
//		}
//		
//		//query single row with BeanPropertyRowMapper (Customer.class)
//		public Customer findByCustomerId2(int custId){
//			 
//			String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
//	 
//			Customer customer = (Customer)getJdbcTemplate().queryForObject(
//					sql, new Object[] { custId }, 
//					new BeanPropertyRowMapper(Customer.class));
//		
//			return customer;
//		}
		
		//query mutiple rows with manual mapping
//		public List<Customer> findAll(){
//			
//			String sql = "SELECT * FROM CUSTOMER";
//			 
//			List<Customer> customers = new ArrayList<Customer>();
//		
//			List<Map> rows = getJdbcTemplate().queryForList(sql);
//			for (Map row : rows) {
//				Customer customer = new Customer();
//				customer.setCustId((Long)(row.get("CUST_ID")));
//				customer.setName((String)row.get("NAME"));
//				customer.setAge((Integer)row.get("AGE"));
//				customers.add(customer);
//			}
//			
//			return customers;
//		}

	@Override
	public void save(Item item) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO items (item_id, sale_price, size, color) VALUES (?, ?, ?, ?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { item.getItem_id(), item.getSale_price(),
						item.getSize(), item.getColor() });
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String sql = "UPDATE items set item_id=?, sale_price=?, size=?, color=?, status=? where item_id=?";
		getJdbcTemplate().update(
				sql,
				new Object[] { item.getItem_id(), item.getSale_price(),
						item.getSize(), item.getColor(), item.isStatus(), item.getItem_id()});
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		String sql = "DELETE from items where item_id = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] { id });
	}

}
