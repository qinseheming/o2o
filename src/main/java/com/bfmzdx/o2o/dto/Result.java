package com.bfmzdx.o2o.dto;

public class Result<T> {
	//是否成功标志
	private boolean success;
	//成功时返回的数据
	private T data;
	//失败时返回的信息
	private String errMsg;
	private int errorCode;
	public Result() {
		super();
	}
	//成功时的构造器
	public Result(boolean success , T data){
		this.success = success;
		this.data = data;
	}
	//失败时的构造器
	public Result(boolean success , int errorCode , String errMsg){
		this.success = success;
		this.errorCode = errorCode;
		this.errMsg = errMsg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
