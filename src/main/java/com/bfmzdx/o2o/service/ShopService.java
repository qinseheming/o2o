package com.bfmzdx.o2o.service;

import com.bfmzdx.o2o.dto.ImageHolder;
import com.bfmzdx.o2o.dto.ShopExecution;
import com.bfmzdx.o2o.entity.Shop;
import com.bfmzdx.o2o.exceptions.ShopOperationException;

public interface ShopService {
	/**
	 * 根据条件分页查询shop
	 * @param shopCondition 查询店铺条件
	 * @param pageIndex 起始页
	 * @param pageSize 每页数据
	 * @return
	 */
	ShopExecution queryShopList(Shop shopCondition ,int pageIndex ,int pageSize);
	/**
	 * 添加店铺
	 * @param shop 要添加的店铺
	 * @param shopImgInputStream 用户上传的店铺缩略图
	 * @param fileName 缩略图名字
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop, ImageHolder shopImageHolder) throws ShopOperationException;
	/**
	 * 通过店铺ID获取店铺信息
	 * @param shopId 店铺ID
	 * @return
	 */
	Shop getShopByShopId(long shopId);
	/**
	 * 修改店铺信息
	 * @param shop 要修改的店铺
	 * @param shopImgInputStream 用户上传的店铺缩略图
	 * @param fileName 缩略图名字
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder shopImageHolder) throws ShopOperationException;
}