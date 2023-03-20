package com.kobb.model;

public class MenuDTO {
	public MenuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String user_id ;
	
	private String menu_name;
	
	private long menu_price;
	
	private long menu_seq;
	
	private long menu_stock;
	
	private String menu_category;
	
	private double menu_img;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public long getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(long menu_price) {
		this.menu_price = menu_price;
	}

	public long getMenu_seq() {
		return menu_seq;
	}

	public void setMenu_seq(long menu_seq) {
		this.menu_seq = menu_seq;
	}

	public long getMenu_stock() {
		return menu_stock;
	}

	public void setMenu_stock(long menu_stock) {
		this.menu_stock = menu_stock;
	}

	public String getMenu_category() {
		return menu_category;
	}

	public void setMenu_category(String menu_category) {
		this.menu_category = menu_category;
	}

	public double getMenu_img() {
		return menu_img;
	}

	public void setMenu_img(double menu_img) {
		this.menu_img = menu_img;
	}

	
	
	
	

}
