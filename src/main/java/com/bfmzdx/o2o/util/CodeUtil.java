package com.bfmzdx.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	/**
	 * 验证码检测，通过返回true，不通过返回false
	 * @param request
	 * @return
	 */
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual").toUpperCase();
		if (verifyCodeActual == null || !verifyCodeExpected.equals(verifyCodeActual)) {
			return false;
		}
		return true;
	}
}
