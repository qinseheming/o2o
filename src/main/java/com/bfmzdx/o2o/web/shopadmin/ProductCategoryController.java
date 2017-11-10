package com.bfmzdx.o2o.web.shopadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bfmzdx.o2o.dto.ProductCategoryExecution;
import com.bfmzdx.o2o.dto.Result;
import com.bfmzdx.o2o.entity.ProductCategory;
import com.bfmzdx.o2o.entity.Shop;
import com.bfmzdx.o2o.enums.ProductCategoryStateEnum;
import com.bfmzdx.o2o.exceptions.ProductCategoryOperationException;
import com.bfmzdx.o2o.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryController {
	@Autowired
	private ProductCategoryService productCategoryService;

	/**
	 * 获取商品分类列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		
		if (currentShop != null && currentShop.getShopId() > 0) {
			List<ProductCategory> queryProductCategoryList = productCategoryService
					.queryProductCategoryList(currentShop.getShopId());
			return new Result<List<ProductCategory>>(true, queryProductCategoryList);
		} else {
			ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
			return new Result<List<ProductCategory>>(false, ps.getState(), ps.getStateInfo());
		}
	}

	/**
	 * 新增商品分类
	 * @param productCategoryList
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addproductcategories", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProductCategories(@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//不使用前台接收的shopId，使用之前存在session里的shopId
		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		for (ProductCategory productCategory : productCategoryList) {
			productCategory.setShopId(shop.getShopId());
			productCategory.setCreateTime(new Date());
		}
		
		if(productCategoryList != null && productCategoryList.size() > 0){
			try {
				ProductCategoryExecution pc = productCategoryService.batchAddProductCategory(productCategoryList);
				if(pc.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
					modelMap.put("success", true);
				}else{
					modelMap.put("success", false);
					modelMap.put("errMsg", pc.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg",e.getMessage());
			}
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}
	@RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,
						currentShop.getShopId());
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类别");
		}
		return modelMap;
	}	
}
