package com.bfmzdx.o2o.web.frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bfmzdx.o2o.entity.HeadLine;
import com.bfmzdx.o2o.entity.ShopCategory;
import com.bfmzdx.o2o.service.HeadLineService;
import com.bfmzdx.o2o.service.ShopCategoryService;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
	@Autowired
	private HeadLineService headLineService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@RequestMapping(value="/listmainpageinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listMainPageInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		try {
			//获取一级店铺列表
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		try {
			//获取可用状态为1的头条列表
			HeadLine headLineCondition = new HeadLine();
			headLineCondition.setEnableStatus(1);
			List<HeadLine> headLineList = headLineService.getHeadLineList(headLineCondition);
			modelMap.put("headLineList", headLineList);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		modelMap.put("success", true);
		return modelMap;
	}
}
