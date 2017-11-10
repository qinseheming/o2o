package com.bfmzdx.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.entity.ProductImg;

public class ProductImgDaoTest extends BaesTest{
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void batchInsertProductImgTest(){
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("图片1");
		productImg1.setImgDesc("测试图片1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("图片2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
	}
	@Test
	@Ignore
	public void deleteAllProductImgsByProductIdTest(){
		long productId = 3;
		int effectedNum = productImgDao.deleteAllProductImgsByProductId(productId);
		System.out.println(effectedNum);
	}
	@Test
	public void getProductImgsByProductIdTest(){
		long productId = 4;
		List<ProductImg> productImgs = productImgDao.getProductImgsByProductId(productId);
		for (ProductImg productImg : productImgs) {
			System.out.println(productImg);
		}
	}
}
