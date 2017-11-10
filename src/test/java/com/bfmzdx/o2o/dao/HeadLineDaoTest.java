package com.bfmzdx.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfmzdx.o2o.BaesTest;
import com.bfmzdx.o2o.entity.HeadLine;

public class HeadLineDaoTest extends BaesTest{
	@Autowired
	private HeadLineDao headLineDao;

	@Test
	public void testQueryArea() {
		List<HeadLine> headLineList = headLineDao.queryHeadLineList(new HeadLine());
		assertEquals(4, headLineList.size());
	}
}
