package com.bfmzdx.o2o.enums;

public enum ProductStateEnum {
	OFFLINE(-1, "非法商品"), 
	DOWN(0, "下架"), 
	SUCCESS(1, "操作成功"), 
	INNER_ERROR(-1001, "操作失败"), 
	EMPTY(-1002, "商品为空");
	
	private int state;
	
	private String sataeInfo;

	private ProductStateEnum(int state, String sataeInfo) {
		this.state = state;
		this.sataeInfo = sataeInfo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSataeInfo() {
		return sataeInfo;
	}

	public void setSataeInfo(String sataeInfo) {
		this.sataeInfo = sataeInfo;
	}
	
	public ProductStateEnum stateOf(int state){
		for (ProductStateEnum productStateEnum : values()) {
			if(productStateEnum.getState() == state){
				return productStateEnum;
			}
		}
		return null;
	}
}
