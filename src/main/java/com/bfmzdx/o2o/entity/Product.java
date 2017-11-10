package com.bfmzdx.o2o.entity;

import java.util.Date;
import java.util.List;
//商品
public class Product {
	private Long productId;
	private String productName;
	private String productDesc;
	//缩略图地址
	private String imgAddr;
	//原价
	private String normalPrice;
	//折扣价
	private String promotionPrice;
	private Integer priority;
	//商品状态(0.下架 1.可用)
	private Integer enableStatus;
	private Date createTime;
	private Date lastEditTime;
	//-------------------------------
	//所属商铺
	private Shop shop;
	//商品图片列表
	private List<ProductImg> productImgList;
	//商品所属类别
	private ProductCategory productCategory;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
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
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public List<ProductImg> getProductImgList() {
		return productImgList;
	}
	public void setProductImgList(List<ProductImg> productImgList) {
		this.productImgList = productImgList;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", imgAddr=" + imgAddr + ", normalPrice=" + normalPrice + ", promotionPrice=" + promotionPrice
				+ ", priority=" + priority + ", enableStatus=" + enableStatus + ", createTime=" + createTime
				+ ", lastEditTime=" + lastEditTime + ", shop=" + shop + ", productImgList=" + productImgList
				+ ", productCategory=" + productCategory + "]";
	}
	
	
}
