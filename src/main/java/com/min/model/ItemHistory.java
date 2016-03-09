package com.min.model;

import java.sql.Timestamp;

public class ItemHistory {
	private String item_id;
	private Timestamp dtime;
	private double original_price;
	private double sale_price;
	private int size;
	private String color;
	private boolean recommendation;
	
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public Timestamp getDtime() {
		return dtime;
	}
	public void setDtime(Timestamp dtime) {
		this.dtime = dtime;
	}
	public double getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}
	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isRecommendation() {
		return recommendation;
	}
	public void setRecommendation(boolean recommendation) {
		this.recommendation = recommendation;
	}
	
}
