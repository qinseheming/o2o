package com.bfmzdx.o2o.service;

import java.util.List;

import com.bfmzdx.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	/**
	 * 根据查询条件获取shopCategory列表
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
