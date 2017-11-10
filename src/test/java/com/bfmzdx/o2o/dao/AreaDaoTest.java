package com.bfmzdx.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.entity.Area;

public class AreaDaoTest extends BaesTest{
	@Autowired
	private AreaDao areaDao;
	@Test
	@Ignore
	public void queryAreaTest(){
		List<Area> areaList = areaDao.queryArea();
		assertEquals(2,areaList.size());
	}

}