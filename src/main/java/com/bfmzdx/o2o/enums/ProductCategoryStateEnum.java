package com.bfmzdx.o2o.enums;

public enum ProductCategoryStateEnum {
	//成功时返回
	SUCCESS(1,"创建成功"),
	//操作失败时返回
	INNER_ERROR(-1001,"操作失败"),
	//为空时返回
	EMPTY_LIST(-1002,"添加数少于1");
	private int state;
	private String stateInfo;
	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
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
	public static ProductCategoryStateEnum stateOf(int index){
		for (ProductCategoryStateEnum state : values()) {
			if(state.getState() == index){
				return state;
			}
		}
		return null;
	}
}