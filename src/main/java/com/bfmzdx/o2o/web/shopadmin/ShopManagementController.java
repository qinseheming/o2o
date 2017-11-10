package com.bfmzdx.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bfmzdx.o2o.dto.ImageHolder;
import com.bfmzdx.o2o.dto.ShopExecution;
import com.bfmzdx.o2o.entity.Area;
import com.bfmzdx.o2o.entity.PersonInfo;
import com.bfmzdx.o2o.entity.Shop;
import com.bfmzdx.o2o.entity.ShopCategory;
import com.bfmzdx.o2o.enums.ShopStateEnum;
import com.bfmzdx.o2o.exceptions.ShopOperationException;
import com.bfmzdx.o2o.service.AreaService;
import com.bfmzdx.o2o.service.ShopCategoryService;
import com.bfmzdx.o2o.service.ShopService;
import com.bfmzdx.o2o.util.CodeUtil;
import com.bfmzdx.o2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId <= 0) {
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			if (currentShopObj == null) {
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shopadmin/shoplist");
			} else {
				Shop currentShop = (Shop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else {
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}
	/**
	 * 获取商店列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getshoplist",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopList(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(owner);
			ShopExecution se = shopService.queryShopList(shopCondition, 1, 100);
			modelMap.put("shopList", se.getShopList());
			request.getSession().setAttribute("shopList", se.getShopList());
			modelMap.put("user", owner);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	/**
	 * 根据商店ID获取商店信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getshopbyshopid" , method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopByShopId(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId <= -1){
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入shopId");
		}else{
			try {
				Shop shop = shopService.getShopByShopId(shopId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}
		return modelMap;
	}
	/**
	 * 获取店铺初始化信息（区域列表和商店类别列表）
	 * @return
	 */
	@RequestMapping(value="getshopinitinfo" ,method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	/**
	 * 注册店铺
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(!CodeUtil.checkVerifyCode(request)){
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 1.接受并转化相应的参数，包括店铺信息及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", "false");
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		//2.注册店铺
		if(shop != null && shopImg != null){
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution se;
			try {
				ImageHolder shopImageHolder = new ImageHolder(shopImg.getInputStream(), shopImg.getOriginalFilename());
				se = shopService.addShop(shop, shopImageHolder);
				if(se.getState() == ShopStateEnum.CHECK.getState()){
					modelMap.put("success", true);
				}else{
					modelMap.put("success", "false");
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}
	/**
	 * 修改店铺信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/modifyshop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(!CodeUtil.checkVerifyCode(request)){
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 1.接受并转化相应的参数，包括店铺信息及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}
		//2.修改店铺
		if(shop != null && shop.getShopId() != null){
			ShopExecution se;
			try {
				if(shopImg == null){
					se = shopService.modifyShop(shop, null);
				}else{
					ImageHolder shopImageHolder = new ImageHolder(shopImg.getInputStream(), shopImg.getOriginalFilename());
					se = shopService.modifyShop(shop, shopImageHolder);
				}
				if(se.getState() == ShopStateEnum.SUCCESS.getState()){
					modelMap.put("success", true);
				}else{
					modelMap.put("success", "false");
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}
}
