package com.bfmzdx.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bfmzdx.o2o.dto.ShopExecution;
import com.bfmzdx.o2o.entity.Area;
import com.bfmzdx.o2o.entity.Shop;
import com.bfmzdx.o2o.entity.ShopCategory;
import com.bfmzdx.o2o.service.AreaService;
import com.bfmzdx.o2o.service.ShopCategoryService;
import com.bfmzdx.o2o.service.ShopService;
import com.bfmzdx.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategorySevice;
	/**
	 * 返回商品列表页里的ShopCategory列表（二级或者一级），以及区域信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listshopspageinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listShopsPageInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if(parentId != -1){
			try {
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				ShopCategory shopCategory = new ShopCategory();
				shopCategory.setParent(parent);
				shopCategoryList = shopCategorySevice.getShopCategoryList(shopCategory);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}else{
			try {
				shopCategoryList = shopCategorySevice.getShopCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			areaList = areaService.getAreaList();
			modelMap.put("success", true);
			modelMap.put("areaList", areaList);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	/**
	 * 获取指定查询条件下的店铺列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="listshops",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listShops(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if(pageIndex >= 0 && pageSize >= 0){
			//获取一级类别ID
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			//获取二级类别ID
			long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
			//获取区域ID
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			//获取模糊查询的名字
			String shopName = HttpServletRequestUtil.getString(request, "shopName");
			//获取组合之后的查询条件
			Shop shopCondition = compactShopCondition4Search(parentId,shopCategoryId,areaId,shopName);
			ShopExecution se = shopService.queryShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "pageIndex and pageSize < 0");
		}
		return modelMap;
	}
	private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
		Shop shopCondition = new Shop();
		if(parentId >= 0){
			ShopCategory parent = new ShopCategory();
			parent.setShopCategoryId(parentId);
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setParent(parent);
			shopCondition.setShopCategory(shopCategory);
		}
	 	if(shopCategoryId >= 0){
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if(areaId >= 0){
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}
		if(shopName != null){
			shopCondition.setShopName(shopName);
		}
		shopCondition.setEnableStatus(1);
		return shopCondition;
	}
}















