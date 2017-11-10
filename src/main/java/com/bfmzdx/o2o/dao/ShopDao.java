package com.bfmzdx.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bfmzdx.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 分页查询店铺列表
	 * 
	 * @param shopCondition
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize 反悔的条数
	 * @return 返回查询到的店铺列表
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	/**
	 * 查询店铺总数
	 * @param shopCondition 查询条件
	 * @return 根据查询条件返回查询到的店铺数量
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * 通过shopId查询店铺
	 * 
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);

	/**
	 * 新增店铺
	 * 
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);

	/**
	 * 更新店铺信息
	 * 
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
