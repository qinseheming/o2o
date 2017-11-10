package com.bfmzdx.o2o.util.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bfmzdx.o2o.dto.UserAccessToken;
import com.bfmzdx.o2o.dto.WechatUser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用来提交https请求给微信获取用户信息
 * @author YangMing
 * @date 2017年11月7日
 */
public class WechatUtil {
	
	private static Logger log = LoggerFactory.getLogger(WechatUtil.class);
	
	/**
	 * 获取UserAccessToken实体类
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public static UserAccessToken getUserAccessToken(String code) throws IOException {
		//测试号信息里的appId
		String appId = "wx9f2ea8418dfc66af";
		//测试号信息里的appsecret
		log.debug("appId:" + appId);
		String appsecret = "fd549270a4c994ad0d47c9f96bb73ffe";
		log.debug("appsecret:" + appsecret);
		
		//根据传入的code，拼接出微信定义好的接口url
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
						+ appId + "&secret=" + appsecret + "&code=" + code 
						+ "&grant_type=authorization_code";
		//向相应的URL发送请求获取taken json字符串
		String tokenStr = httpsRequest(url,"GET",null);
		log.debug("userAccessToken:" + tokenStr);
		UserAccessToken token = new UserAccessToken();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			token = objectMapper.readValue(tokenStr, UserAccessToken.class);
		} catch (JsonParseException e) {
			log.error("获取用户accessToken失败: " + e.getMessage()); 
			e.printStackTrace(); 
		} catch (JsonMappingException e) {
			log.error("获取用户accessToken失败: " + e.getMessage()); 
			e.printStackTrace(); 
		} catch (IOException e) {
			log.error("获取用户accessToken失败: " + e.getMessage());
			e.printStackTrace(); 
		}
		if(token == null) {
			log.error("获取用户accessToken失败");
			return null;
		}
		return token;
	}
	/**
	 * 获取WechatUser实体类
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static WechatUser getUserInfo(String accessToken, String openId) {
		//根据传入的accessToken及openId拼接出访问微信定义的端口并获取用户信息的url
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" 
						+ accessToken + "&openid=" + openId + "&lang=zh_CN";
		//访问该url获取用户信息json字符串
		String userStr = httpsRequest(url, "GRT", null);
		log.debug("userStr:" + userStr);
		WechatUser user = new WechatUser();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			user = objectMapper.readValue(userStr, WechatUser.class);
		} catch (JsonParseException e) {
			log.error("获取用户信息失败: " + e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error("获取用户信息失败: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("获取用户信息失败: " + e.getMessage());
			e.printStackTrace();
		}
		if(user == null) {
			log.error("获取用户信息失败 ");
			return null;
		}
		return user;
	}
	
	/**
	 * 发起https请求并获取结果
	 * @param url	请求地址
	 * @param string	请求方式（get、post）
	 * @param object	提交的数据
	 * @return	json字符串
	 */
	private static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			//创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = {new MyX509TrutsManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			//从上述sslContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
			httpsUrlConn.setSSLSocketFactory(ssf);
			httpsUrlConn.setDoOutput(true);
			httpsUrlConn.setDoInput(true);
			httpsUrlConn.setUseCaches(false);
			//设置请求方式
			httpsUrlConn.setRequestMethod(requestMethod);
			if("GET".equalsIgnoreCase(requestMethod)) {
				httpsUrlConn.connect();
			}
			//当有数据需要提交时
			if(null != outputStr) {
				OutputStream outputStream = httpsUrlConn.getOutputStream();
				//注意编码格式防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			//将返回的输入流转换成字符串
			InputStream inputStream = httpsUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str = null;
			while((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			//释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpsUrlConn.disconnect();
			log.debug("https buffer:" + buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out");
		} catch (Exception e) {
			log.error("http request error :{}", e);
		}
		return buffer.toString();
	}
}
