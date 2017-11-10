package com.bfmzdx.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.entity.ProductCategory;

public class ProductCategoryTest extends BaesTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Test
	@Ignore
	public void batchInsertProductCategoryTest(){
		List<ProductCategory> productCategoryList = new ArrayList<>();
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("测试商品类别4");
		productCategory.setPriority(4);
		productCategory.setShopId(1L);
		productCategory.setCreateTime(new Date());
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryName("测试商品类别5");
		productCategory1.setPriority(5);
		productCategory1.setShopId(2L);
		productCategory1.setCreateTime(new Date());
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory1);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		System.out.println(effectedNum);
	}
	@Test
	@Ignore
	public void queryProductCategoryListTest(){
		int shopId = 1;
		List<ProductCategory> queryProductCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println(queryProductCategoryList.size());
		System.out.println(queryProductCategoryList.get(0).getProductCategoryName());
	}
	@Test
	@Ignore
	public void updateProductCategoryTest(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(1L);
		productCategory.setPriority(2);
		productCategory.setProductCategoryName("修改商品类别1");
		int effectedNum = productCategoryDao.updateProductCategory(productCategory);
		System.out.println(effectedNum);
	}
	@Test
//	@Ignore
	public void deleteProductCategoryTest(){
		long shopId = 1L;
		List<ProductCategory> queryProductCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for (ProductCategory productCategory : queryProductCategoryList) {
			if("1".equals(productCategory.getProductCategoryName())){
				int effectedNum = productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(), shopId);
				assertEquals(1,effectedNum);
			}
		}
	}
}
