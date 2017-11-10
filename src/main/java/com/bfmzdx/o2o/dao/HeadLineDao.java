package com.bfmzdx.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bfmzdx.o2o.entity.HeadLine;

public interface HeadLineDao {
	
	/**
	 * 根据传入的条件（头条名查询头条）
	 * @param headLineList
	 * @return
	 */
	List<HeadLine> queryHeadLineList(@Param("headLineCondition")HeadLine headLineCondition);;
	
}
