package com.elife.classproject.project.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryGoodsModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<GoodDetailModel> item_list;
	public String currentPage;
	public String total;
	public int totalPage;

	public class GoodDetailModel{

		public int id;
		public String cate_id;
		public String title;
		public String img;
		public String price;
		public String tuan_price;
		public String standard;
		public String add_time;
		public String item_id;

	}
}
