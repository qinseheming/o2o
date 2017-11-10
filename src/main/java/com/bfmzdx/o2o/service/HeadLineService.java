package com.bfmzdx.o2o.service;

import java.io.IOException;
import java.util.List;

import com.bfmzdx.o2o.entity.HeadLine;

public interface HeadLineService {
	/**
	 * 根据指定的条件返回指定的头条列表
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition)throws IOException;
}
