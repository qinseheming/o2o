package com.bfmzdx.o2o.dao;

import java.util.List;

import com.bfmzdx.o2o.entity.ProductImg;

public interface ProductImgDao {
	/**
	 * 批量添加商品详情图
	 * @param productList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productList);
	/**
	 * 删除商品ID为productId下的所有商品详情图
	 * @param productId
	 * @return
	 */
	int deleteAllProductImgsByProductId(long productId);
	/**
	 * 查询ID为productId的所有详情图片
	 * @param productId
	 * @return
	 */
	List<ProductImg> getProductImgsByProductId(long productId);
}
