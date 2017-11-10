package com.bfmzdx.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.dto.ImageHolder;
import com.bfmzdx.o2o.dto.ShopExecution;
import com.bfmzdx.o2o.entity.Area;
import com.bfmzdx.o2o.entity.PersonInfo;
import com.bfmzdx.o2o.entity.Shop;
import com.bfmzdx.o2o.entity.ShopCategory;
import com.bfmzdx.o2o.enums.ShopStateEnum;
import com.bfmzdx.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaesTest{
	@Autowired
	private ShopService shopService;
	
	@Test
//	@Ignore
	public void addShopTest() throws ShopOperationException, FileNotFoundException{
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(5);
		shopCategory.setShopCategoryId(43L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺3");
		shop.setShopDesc("测试描述3");
		shop.setShopAddr("测试地址3");
		shop.setPhone("测试电话3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("C:/Users/Administrator/Pictures/超级管理员系统.JPG");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder shopImageHolder = new ImageHolder(is, shopImg.getName());
		ShopExecution se = shopService.addShop(shop, shopImageHolder );
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	@Test 
	@Ignore
	public void modifyShopTest() throws FileNotFoundException{
		Shop shop = shopService.getShopByShopId(2L);
		shop.setShopName("修改店铺3");
		//TODO 未添加图片的店铺修改时添加图片失败
		File shopImg = new File("C:/Users/Administrator/Pictures/前段展示系统.PNG");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder shopImageHolder = new ImageHolder(is, shopImg.getName());
		ShopExecution shopExecution = shopService.modifyShop(shop, shopImageHolder);
		System.out.println(shopExecution.getShop().getShopName());
	}
	@Test
	@Ignore
	public void queryShopListTest(){
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		ShopExecution se = shopService.queryShopList(shopCondition, 1, 100);
		System.out.println(se.getShopList().size());
	}

}
