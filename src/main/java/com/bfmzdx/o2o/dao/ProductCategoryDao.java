package com.bfmzdx.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bfmzdx.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 批量插入商品类别
	 * @param productCategoryList
	 * @return 返回插入成功的行数
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	/**
	 * 根据商品类别ID和商店ID删除相应的商品类别
	 * @param productCategoryId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
	/**
	 * 查询指定店铺的商品类别列表
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	
	int updateProductCategory(ProductCategory productCategory);
}
