package com.elife.classproject.network;

public class GoodModel {
	private int id;
	private String goodNum;
	private String goodName;
	private double goodPrice;
	private String goodShop;
	private String goodImg;
	private double goodPostFee;
	
	public GoodModel() {
		
	}
	
	
	public GoodModel(int id, String goodNum, String goodName, double goodPrice, String goodShop, String goodImg,
			double goodPostFee) {
		super();
		this.id = id;
		this.goodNum = goodNum;
		this.goodName = goodName;
		this.goodPrice = goodPrice;
		this.goodShop = goodShop;
		this.goodImg = goodImg;
		this.goodPostFee = goodPostFee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodNum() {
		return goodNum;
	}
	public void setGoodNum(String goodNum) {
		this.goodNum = goodNum;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public double getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}
	public String getGoodShop() {
		return goodShop;
	}
	public void setGoodShop(String goodShop) {
		this.goodShop = goodShop;
	}
	public String getGoodImg() {
		return goodImg;
	}
	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}
	public double getGoodPostFee() {
		return goodPostFee;
	}
	public void setGoodPostFee(double goodPostFee) {
		this.goodPostFee = goodPostFee;
	}
	
	
	
}
