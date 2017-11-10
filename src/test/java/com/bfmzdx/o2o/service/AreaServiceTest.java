package com.bfmzdx.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.entity.Area;

public class AreaServiceTest extends BaesTest{
	@Autowired
	private AreaService areaService;
	@Test
	public void getAreaListTest(){
		List<Area> areaList = areaService.getAreaList();
		assertEquals("文昌路",areaList.get(0).getAreaName());
	}
}
