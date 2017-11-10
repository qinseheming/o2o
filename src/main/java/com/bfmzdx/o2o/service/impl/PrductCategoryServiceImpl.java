package com.bfmzdx.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfmzdx.o2o.dao.ProductCategoryDao;
import com.bfmzdx.o2o.dao.ProductDao;
import com.bfmzdx.o2o.dto.ProductCategoryExecution;
import com.bfmzdx.o2o.entity.ProductCategory;
import com.bfmzdx.o2o.enums.ProductCategoryStateEnum;
import com.bfmzdx.o2o.exceptions.ProductCategoryOperationException;
import com.bfmzdx.o2o.service.ProductCategoryService;

@Service
public class PrductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Autowired
	private ProductDao productDao;
	@Override
	public List<ProductCategory> queryProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectedNum > 0){
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}else{
					throw new ProductCategoryOperationException("店铺类别创建失败");
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("批量添加商品类别异常：" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		// 将该类别下的商品的类别id置为空
		try {
			int effectedNum = productDao.updateProductCategoryIdToNull(productCategoryId);
			if(effectedNum < 0){
				throw new ProductCategoryOperationException("商品类别更新失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory error"+e.getMessage());
		}
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum <= 0){
				throw new ProductCategoryOperationException("商品类别删除失败");
			}else{
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (ProductCategoryOperationException e) {
			throw new ProductCategoryOperationException("商品类别删除失败："+e.getMessage());
		}
	}

}
