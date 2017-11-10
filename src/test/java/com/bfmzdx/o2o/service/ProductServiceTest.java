package com.bfmzdx.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.dto.ImageHolder;
import com.bfmzdx.o2o.dto.ProductExecution;
import com.bfmzdx.o2o.entity.Product;
import com.bfmzdx.o2o.entity.ProductCategory;
import com.bfmzdx.o2o.entity.Shop;
import com.bfmzdx.o2o.enums.ProductStateEnum;
import com.bfmzdx.o2o.exceptions.ShopOperationException;

public class ProductServiceTest extends BaesTest{
	@Autowired
	private ProductService productService;
	
	@Test
	@Ignore
	public void addProductTest() throws FileNotFoundException{
		//创建shopId为1且productCategoryId为1的商品实例并给其成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(1L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		// 创建缩略图文件流
		File thumbnailFile = new File("C:/Users/Administrator/Pictures/images/item/shop/15/2017060522042982266.png");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(is ,thumbnailFile.getName() );
		// 创建两个商品详情图文件流并将他们添加到详情图列表中
		File productImg1 = new File("C:/Users/Administrator/Pictures/images/item/shop/15/2017060523302118864.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("C:/Users/Administrator/Pictures/images/item/shop/15/20170605233021865310.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(is1 ,productImg1.getName() ));
		productImgList.add(new ImageHolder(is2 ,productImg2.getName() ));
		// 添加商品并验证
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
	@Test
	public void testModifyProduct() throws ShopOperationException, FileNotFoundException {
		// 创建shopId为1且productCategoryId为1的商品实例并给其成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(1L);
		product.setProductId(4L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("正式的商品");
		product.setProductDesc("正式的商品");
		// 创建缩略图文件流
		File thumbnailFile = new File("C:/Users/Administrator/Pictures/images/item/shop/17/2017060609084595067.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(is ,thumbnailFile.getName() );
		// 创建两个商品详情图文件流并将他们添加到详情图列表中
		File productImg1 = new File("C:/Users/Administrator/Pictures/images/item/shop/16/2017060608534289617.png");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("C:/Users/Administrator/Pictures/images/item/shop/16/2017060608574074561.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(is1,productImg1.getName() ));
		productImgList.add(new ImageHolder(is2,productImg2.getName() ));
		// 添加商品并验证
		ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
}
