package com.bfmzdx.o2o.entity;

import java.util.Date;

public class LocalAuth {
	private Long localAuthId;
	private String username;
	private String password;
	private Date createTime;
	
	private PersonInfo personInfo;

	public Long getLocalAuthId() {
		return localAuthId;
	}

	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "LocalAuth [localAuthId=" + localAuthId + ", username=" + username + ", password=" + password
				+ ", createTime=" + createTime + ", personInfo=" + personInfo + "]";
	}
	
}
