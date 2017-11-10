package com.bfmzdx.o2o.dto;

import java.util.List;

import com.bfmzdx.o2o.entity.Product;
import com.bfmzdx.o2o.enums.ProductStateEnum;

public class ProductExecution {
	// 结果状态
	private int state;
	// 状态信息
	private String stateInfo;

	private Product product;

	private int count;
	
	private List<Product> productList;

	public ProductExecution() {
	}

	// 操作失败时的构造器
	public ProductExecution(ProductStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getSataeInfo();
	}

	// 对单个商品操作成功时的构造器
	public ProductExecution(ProductStateEnum stateEnum, Product product) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getSataeInfo();
		this.product = product;
	}

	// 对多个商品操作成功时的构造器
	public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getSataeInfo();
		this.productList = productList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

}
