package com.bfmzdx.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfmzdx.o2o.dao.ShopDao;
import com.bfmzdx.o2o.dto.ImageHolder;
import com.bfmzdx.o2o.dto.ShopExecution;
import com.bfmzdx.o2o.entity.Shop;
import com.bfmzdx.o2o.enums.ShopStateEnum;
import com.bfmzdx.o2o.exceptions.ShopOperationException;
import com.bfmzdx.o2o.service.ShopService;
import com.bfmzdx.o2o.util.ImageUtil;
import com.bfmzdx.o2o.util.PageUtil;
import com.bfmzdx.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder shopImageHolder)
			throws ShopOperationException {
		// 空值判断
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			// 给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (shopImageHolder.getImgInputStream() != null) {
					// 存储图片
					try {
						addShopImg(shop, shopImageHolder);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder shopImageHolder) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImageHolder, dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getShopByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder shopImageHolder)
			throws ShopOperationException {
		// 判断shop是否为空，为空返回错误信息
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			try {
				// 1.判断是否需要处理图片
				if (shopImageHolder.getImgInputStream() != null && shopImageHolder.getImgName() != null && !"".equals(shopImageHolder.getImgName())) {
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, shopImageHolder);
				}
				shop.setLastEditTime(new Date());
				int effectedNum = shopDao.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				}else{
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new ShopOperationException("修改店铺Service异常" + e.getMessage());
			}

		}

	}

	@Override
	public ShopExecution queryShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageUtil.getRowIndexByPageIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if(count > 0){
			se.setShopList(shopList);
			se.setCount(count);
		}else{
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}

}