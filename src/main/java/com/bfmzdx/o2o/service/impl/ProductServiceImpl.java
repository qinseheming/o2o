package com.bfmzdx.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfmzdx.o2o.dao.ProductDao;
import com.bfmzdx.o2o.dao.ProductImgDao;
import com.bfmzdx.o2o.dto.ImageHolder;
import com.bfmzdx.o2o.dto.ProductExecution;
import com.bfmzdx.o2o.entity.Product;
import com.bfmzdx.o2o.entity.ProductImg;
import com.bfmzdx.o2o.enums.ProductStateEnum;
import com.bfmzdx.o2o.exceptions.ProductCategoryOperationException;
import com.bfmzdx.o2o.service.ProductService;
import com.bfmzdx.o2o.util.ImageUtil;
import com.bfmzdx.o2o.util.PageUtil;
import com.bfmzdx.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder productImgHolder,
			List<ImageHolder> productImgListHolder) {
		// 判断空值
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 赋初值
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			// 判断缩略图是否为空，不为空则创建缩略图并把相对地址赋给product
			if (productImgHolder != null) {
				addProductImg(product, productImgHolder);
			}
			try {
				// 将product存到数据库
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("添加商品失败");
				}
			} catch (ProductCategoryOperationException e) {
				throw new ProductCategoryOperationException("添加商品异常" + e.getMessage());
			}
			// 判断商品详情图片是否为空，不为空则添加
			if (productImgListHolder != null) {
				addProductImgList(product, productImgListHolder);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}

	}

	@Override
	@Transactional
	public ProductExecution deleteProduct(long productId, long shopId) {
		ProductExecution pe = null;
		if (productId <= 0) {
			pe = new ProductExecution(ProductStateEnum.EMPTY);
			return pe;
		}
		try {
			productImgDao.deleteAllProductImgsByProductId(productId);
			int effectedNum = productDao.deleteProductByProductId(productId, shopId);
			if (effectedNum > 0) {
				return new ProductExecution(ProductStateEnum.SUCCESS);
			}
		} catch (ProductCategoryOperationException e) {
			throw new ProductCategoryOperationException("删除商品失败" + e.getMessage());
		}
		return null;
	}

	// 根据ID查找商品
	@Override
	public Product getProductById(long productId) {
		return productDao.getProductByProductId(productId);
	}

	// 修改商品
	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageHolder productImgHolder,
			List<ImageHolder> productImgListHolder) {
		if (product == null || product.getShop() == null || product.getShop().getShopId() == null) {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
		product.setLastEditTime(new Date());
		if (productImgHolder != null) {
			Product tempProduct = productDao.getProductByProductId(product.getProductId());
			if (tempProduct.getImgAddr() != null) {
				ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
			}
			addProductImg(product, productImgHolder);
		}
		if (productImgListHolder != null) {
			deleteProductImgList(product, productImgListHolder);
			addProductImgList(product, productImgListHolder);
		}
		try {
			int effectedNum = productDao.updateProduct(product);
			if (effectedNum <= 0) {
				throw new ProductCategoryOperationException("更新商品信息失败");
			}
			return new ProductExecution(ProductStateEnum.SUCCESS,product);
		} catch (ProductCategoryOperationException e) {
			throw new ProductCategoryOperationException("修改商品异常" + e.getMessage());
		}
	}

	// 分页查询商品
	@Override
	public ProductExecution getProducts(Product product, int pageIndex, int pageSize) {
		int rowIndex = PageUtil.getRowIndexByPageIndex(pageIndex, pageSize);
		List<Product> productList = productDao.getProducts(product, rowIndex, pageSize);
		int count = productDao.queryProductCount(product);
		ProductExecution pe = new ProductExecution();
		if (count > 0) {
			pe.setCount(count);
			pe.setProductList(productList);
		} else {
			pe.setState(ProductStateEnum.INNER_ERROR.getState());
		}
		return pe;
	}

	/**
	 * 批量添加商品详情图
	 * 
	 * @param product
	 * @param productImgListHolder
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgListHolder) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<>();
		// 处理图片
		for (ImageHolder productImgHolder : productImgListHolder) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setCreateTime(new Date());
			productImg.setProductId(product.getProductId());
			productImg.setImgAddr(imgAddr);
			productImgList.add(productImg);
		}
		// 将详情图片保存到数据库
		if (productImgListHolder.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("批量添加图片异常");
				}
			} catch (ProductCategoryOperationException e) {
				throw new ProductCategoryOperationException("批量添加图片异常：" + e.getMessage());
			}
		}

	}
	/**
	 * 批量删除商品详情图
	 * 	先删除硬盘上的
	 * 	再删除数据库里的
	 * @param product
	 * @param productImgListHolder
	 */
	private void deleteProductImgList(Product product, List<ImageHolder> productImgListHolder){
		List<ProductImg> productImgList = productImgDao.getProductImgsByProductId(product.getProductId());
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		productImgDao.deleteAllProductImgsByProductId(product.getProductId());
	}
	/**
	 * 添加商品缩略图
	 * 
	 * @param product
	 * @param productImgHolder
	 */
	private void addProductImg(Product product, ImageHolder productImgHolder) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(productImgHolder, dest);
		product.setImgAddr(thumbnailAddr);
	}

}
