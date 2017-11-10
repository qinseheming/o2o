package com.bfmzdx.o2o.util.wechat;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
/**
 * 证书信任管理器（用于Https请求）
 * @author YangMing
 * @date 2017年11月7日
 */
public class MyX509TrutsManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
