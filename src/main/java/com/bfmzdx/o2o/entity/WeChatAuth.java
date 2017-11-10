package com.bfmzdx.o2o.entity;

import java.util.Date;

//微信账号
public class WeChatAuth {
	private Long weChatAuthId;
	//微信账号和公众号绑定的唯一标识
	private String openId;
	private Date createTime;
	
	private PersonInfo personInfo;

	public Long getWeChatAuthId() {
		return weChatAuthId;
	}

	public void setWeChatAuthId(Long weChatAuthId) {
		this.weChatAuthId = weChatAuthId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	@Override
	public String toString() {
		return "WeChatAuth [weChatAuthId=" + weChatAuthId + ", openId=" + openId + ", createTime=" + createTime
				+ ", personInfo=" + personInfo + "]";
	}
	
}
