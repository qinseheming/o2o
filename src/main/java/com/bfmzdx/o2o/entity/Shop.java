package com.bfmzdx.o2o.entity;

import java.util.Date;
//店铺
public class Shop {
	private Long shopId;
	private String shopName;
	private String shopDesc;
	private String phone;
	private String shopAddr;
	private Integer priority;
	private String shopImg;
	//店铺状态(-1.不可用 0.审核中 1.可用)
	private Integer enableStatus;
	//建议
	private String advice;
	private Date createTime;
	private Date lastEditTime;
	//---------------------------
	//店铺所在区域
	private Area area;
	//店铺所属类别
	private ShopCategory shopCategory;
	//店铺所属用户
	private PersonInfo owner;
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}
	public PersonInfo getOwner() {
		return owner;
	}
	public void setOwner(PersonInfo owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", shopDesc=" + shopDesc + ", phone=" + phone
				+ ", shopAddr=" + shopAddr + ", priority=" + priority + ", shopImg=" + shopImg + ", enableStatus="
				+ enableStatus + ", advice=" + advice + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime
				+ ", area=" + area + ", shopCategory=" + shopCategory + ", owner=" + owner + "]";
	}
	
	
}
