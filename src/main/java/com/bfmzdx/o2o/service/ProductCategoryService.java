package com.bfmzdx.o2o.service;

import java.util.List;

import com.bfmzdx.o2o.dto.ProductCategoryExecution;
import com.bfmzdx.o2o.entity.ProductCategory;
import com.bfmzdx.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {

	/**
	 * 查询指定店铺的商品分类
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);

	/**
	 * 批量添加商品类别
	 * 
	 * @param productCategoryList
	 * @return
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;
	/**
	 * 先将该类别下的商品的类别id置为空，再删除该商品类别
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId , long shopId)
			throws ProductCategoryOperationException;
}
