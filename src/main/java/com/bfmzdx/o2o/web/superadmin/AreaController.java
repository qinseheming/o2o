package com.bfmzdx.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bfmzdx.o2o.entity.Area;
import com.bfmzdx.o2o.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	Logger logger = LoggerFactory.getLogger(AreaController.class);
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/listarea" ,method=RequestMethod.GET)
	@ResponseBody
	public Map<String , Object> listArea(){
		logger.info("===start===");
		long startTime = System.currentTimeMillis();
		Map<String , Object> modelMap = new HashMap<String,Object>();
		List<Area> areaList = new ArrayList<Area>();
		try{
			areaList = areaService.getAreaList();
			modelMap.put("rows", areaList);
			modelMap.put("total", areaList.size());
		}catch(Exception e){
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		logger.error("errorTest");
		long endTime = System.currentTimeMillis();
		logger.debug("costTime:[{}ms]",endTime-startTime);
		logger.info("===end===");
		return modelMap;
	}
}
