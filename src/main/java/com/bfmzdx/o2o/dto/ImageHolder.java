package com.bfmzdx.o2o.dto;

import java.io.InputStream;
/**
 * 对图片流和图片名字的封装
 * @author Administrator
 *
 */
public class ImageHolder {
	//图片流
	private InputStream imgInputStream;
	//图片名字
	private String imgName;

	public ImageHolder(InputStream imgInputStream, String imgName) {
		super();
		this.imgInputStream = imgInputStream;
		this.imgName = imgName;
	}

	public InputStream getImgInputStream() {
		return imgInputStream;
	}

	public void setImgInputStream(InputStream imgInputStream) {
		this.imgInputStream = imgInputStream;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
}
