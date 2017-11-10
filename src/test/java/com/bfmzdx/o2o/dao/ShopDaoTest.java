package com.bfmzdx.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.entity.Area;
import com.bfmzdx.o2o.entity.PersonInfo;
import com.bfmzdx.o2o.entity.Shop;
import com.bfmzdx.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaesTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void insertShopTest(){
		Area area = new Area();
		area.setAreaId(1);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		Shop shop = new Shop();
		shop.setArea(area);
		shop.setAdvice("测试建议1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setOwner(owner);
		shop.setPhone("13812345678");
		shop.setPriority(1);
		shop.setShopAddr("测试地址1");
		shop.setShopCategory(shopCategory);
		shop.setShopDesc("测试描述1");
		shop.setShopName("测试店铺1");
		int effectNum = shopDao.insertShop(shop);
		assertEquals(1,effectNum);
	}
	@Test
	@Ignore
	public void updateShopTest(){
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopDesc("修改描述1");
		shop.setShopName("修改店铺1");
		shop.setLastEditTime(new Date());
		int effectNum = shopDao.updateShop(shop);
		assertEquals(1,effectNum);
	}
	@Test
	@Ignore
	public void queryByShopIdTest(){
		long shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println(shop.getArea().getAreaId());
		System.out.println(shop.getArea().getAreaName());
	}
	@Test
	public void queryShopListAndCountTest(){
		Shop shopCondition = new Shop();
		ShopCategory childCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(12L);
		childCategory.setParent(parentCategory);
		shopCondition.setShopCategory(childCategory);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 6);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺列表的大小：" + shopList.size());
		System.out.println("店铺总数：" + count);		
	}
}
