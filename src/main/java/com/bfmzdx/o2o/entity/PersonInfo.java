package com.bfmzdx.o2o.entity;

import java.util.Date;

public class PersonInfo {
	private Long userId;
	private String name;
	private String gender;
	//头像地址
	private String profileImg;
	private String email;
	private Date createTime;
	private Date lastEditTime;
	//用户状态 （0.禁用 1.可用）
	private Integer enableStatus;
	//用户类型 （1.顾客 2.商家 3.超级管理员）
	private Integer userType;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "PersonInfo [userId=" + userId + ", name=" + name + ", gender=" + gender + ", profileImg=" + profileImg
				+ ", email=" + email + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime
				+ ", enableStatus=" + enableStatus + ", userType=" + userType + "]";
	}
	
}
