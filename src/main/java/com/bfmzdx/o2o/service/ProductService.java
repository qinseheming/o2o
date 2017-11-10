package com.bfmzdx.o2o.service;

import java.util.List;

import com.bfmzdx.o2o.dto.ImageHolder;
import com.bfmzdx.o2o.dto.ProductExecution;
import com.bfmzdx.o2o.entity.Product;

public interface ProductService {
	
	/**
	 * 添加商品
	 * @param product 商品
	 * @param productImgHolder 商品缩略图
	 * @param productImgListHolder 商品详情图
	 * @return
	 */
	ProductExecution addProduct(Product product,ImageHolder productImgHolder, List<ImageHolder> productImgListHolder);
	/**
	 * 删除该商品下的所有详情图片，再删除该商品
	 * @param productId
	 * @return
	 */
	ProductExecution deleteProduct(long productId,long shopId);
	/**
	 * 通过ID查询单个商品
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);
	/**
	 * 修改商品属性
	 * @param product
	 * @param productImgHolder
	 * @param productImgListHolder
	 * @return
	 */
	ProductExecution modifyProduct(Product product,ImageHolder productImgHolder, List<ImageHolder> productImgListHolder);
	/**
	 * 分页查询商品
	 * @param product
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProducts(Product product ,int pageIndex,int pageSize);
}
