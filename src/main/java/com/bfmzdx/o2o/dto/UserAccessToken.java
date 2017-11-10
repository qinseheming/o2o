package com.bfmzdx.o2o.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用户AccessToken实体类，用来接收accessToken及openId等信息
 * @author YangMing
 * @date 2017年11月7日
 */
public class UserAccessToken {
	//获取到的凭证
	@JsonProperty("access_token")
	private String accessToken;
	//凭证有效时间，单位：秒
	@JsonProperty("expires_in")
	private String expiresIn;
	//表示更新令牌，用来获取下一次访问令牌，这里没用上
	@JsonProperty("refresh_token")
	private String refreshToken;
	//用户在此公众号下的唯一标识
	@JsonProperty("openid")
	private String openId;
	//标识权限范围，这里可省略
	@JsonProperty("scope")
	private String scope;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "UserAccessToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", refreshToken="
				+ refreshToken + ", openId=" + openId + ", scope=" + scope + "]";
	}
	
}