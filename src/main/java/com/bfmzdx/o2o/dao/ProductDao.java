package com.bfmzdx.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bfmzdx.o2o.entity.Product;

public interface ProductDao {
	/**
	 * 添加商品
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	/**
	 * 根据商品ID删除该商品
	 * @param product
	 * @return
	 */
	int deleteProductByProductId(@Param("productId")long productId,@Param("shopId")long shopId);
	/**
	 * 根据商品ID查询该商品
	 * @param shopId
	 * @return
	 */
	Product getProductByProductId(long productId);
	/**
	 * 更新商品
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	/**
	 * 查询商品总数
	 * @param shopId
	 * @return
	 */
	int queryProductCount(@Param("productCondition")Product product);
	/**
	 * 分页查询该店铺下所有商品,可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
	 * @param shopId
	 * @return
	 */
	List<Product> getProducts(@Param("productCondition")Product productCondition,@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	/**
	 * 删除商品类别之前，将商品类别ID置为空
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryIdToNull(long productCategoryId);
	
}
