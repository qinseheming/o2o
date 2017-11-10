package com.bfmzdx.o2o.util;

public class PageUtil {
	public static int getRowIndexByPageIndex(int pageIndex ,int pageSize){
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize :0;
	}
}
