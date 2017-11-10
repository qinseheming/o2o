package com.bfmzdx.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaesTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	
	//测试查询父ID为空的类别（一级分类）
	@Test
//	@Ignore
	public void queryShopCategoryTest1(){
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
		assertEquals(6, shopCategoryList.size());
	}
	
	
	//查询父ID不为空的类别（二级分类）
	@Test
	@Ignore
	public void queryShopCategoryTest2(){
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(2,shopCategoryList.size());
		ShopCategory testCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(1L);
		testCategory.setParent(parentCategory);
		shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
		assertEquals(1, shopCategoryList.size());
		System.out.println(shopCategoryList.get(0).getShopCategoryName());
		//测试查询父ID为空的类别（一级分类）
//		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
//		System.out.println(shopCategoryList.size());
		
	}
}
