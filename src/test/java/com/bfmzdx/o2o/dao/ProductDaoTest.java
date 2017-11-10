package com.bfmzdx.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.entity.Product;
import com.bfmzdx.o2o.entity.ProductCategory;
import com.bfmzdx.o2o.entity.Shop;

public class ProductDaoTest extends BaesTest{

	@Autowired
	private ProductDao productDao;
	@Test
	@Ignore
	public void insertProductTest(){
		Product product = new Product();
		product.setProductId(1L);
		product.setProductName("测试商品缩略图");
		product.setProductDesc("测试商品简介");
		product.setCreateTime(new Date());
		product.setLastEditTime(new Date());
		product.setEnableStatus(1);
		product.setImgAddr("测试图片地址");
		product.setNormalPrice("100");
		product.setPromotionPrice("80");
		product.setPriority(1);
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(1L);
		product.setShop(shop);
		product.setProductCategory(productCategory);
		int effectedNum = productDao.insertProduct(product);
		System.out.println(effectedNum);
	}
	@Test
	@Ignore
	public void deleteProductByProductId(){
		long productId = 3;
		long shopId = 1;
		int effectedNum = productDao.deleteProductByProductId(productId,shopId);
		System.out.println(effectedNum);
	}
	
	@Test
	@Ignore
	public void getProductByProductId(){
		long productId = 1;
		Product product = productDao.getProductByProductId(productId);
		System.out.println(product);
	}
	
	@Test
	@Ignore
	public void updateProductTest() throws Exception {
		Product product = new Product();
		ProductCategory pc = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(1L);
		pc.setProductCategoryId(1L);
		product.setProductId(5L);
		product.setShop(shop);
		product.setProductName("第二个产品");
		product.setProductCategory(pc);
		// 修改productId为1的商品的名称
		// 以及商品类别并校验影响的行数是否为1
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void getProductsAndCountTest(){
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(1L);
		Product product = new Product();
		product.setShop(shop);
		product.setProductCategory(productCategory);
//		product.setProductName("测试商品缩略图");
		List<Product> productList = new ArrayList<>();
		productList = productDao.getProducts(product, 0, 10);
		System.out.println(productList.size());
		int count = productDao.queryProductCount(product);
		System.out.println("count:"+count);
	}
	@Test
	@Ignore
	public void testEUpdateProductCategoryToNull() {
		// 将productCategoryId为2的商品类别下面的商品的商品类别置为空
		int effectedNum = productDao.updateProductCategoryIdToNull(3L);
		assertEquals(1, effectedNum);
	}
}










