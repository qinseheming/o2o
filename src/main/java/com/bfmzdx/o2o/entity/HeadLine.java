package com.bfmzdx.o2o.entity;

import java.util.Date;

//头条
public class HeadLine {
	//头条ID
	private Long lineId;
	//头条名称
	private String lineName;
	//头条链接
	private String linelink;
	//头条图片
	private String lineImg;
	//优先级
	private Integer priority;
	//状态(0.可用 1.禁用)
	private Integer enableStatus;
	private Date createTime;
	private Date lastEditTime;
	
	public Long getLineId() {
		return lineId;
	}
	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLinelink() {
		return linelink;
	}
	public void setLinelink(String linelink) {
		this.linelink = linelink;
	}
	public String getLineImg() {
		return lineImg;
	}
	public void setLineImg(String lineImg) {
		this.lineImg = lineImg;
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
	@Override
	public String toString() {
		return "HeadLine [lineId=" + lineId + ", lineName=" + lineName + ", linelink=" + linelink + ", lineImg="
				+ lineImg + ", priority=" + priority + ", enableStatus=" + enableStatus + ", createTime=" + createTime
				+ ", lastEditTime=" + lastEditTime + "]";
	}
	
}
