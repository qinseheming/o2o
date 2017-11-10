package com.bfmzdx.o2o.web.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bfmzdx.o2o.dto.UserAccessToken;
import com.bfmzdx.o2o.dto.WechatUser;
import com.bfmzdx.o2o.util.wechat.WechatUtil;

/**
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9f2ea8418dfc66af&redirect_uri=http://o2o.dzswpt.top/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * 则这里将会获取到code，之后再可以通过code获取到access_token进而获取到用户信息
 * @author YangMing
 * @date 2017年11月7日
 */
@Controller
@RequestMapping("wechatlogin")
public class WechatLoginController {
	
	private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);
	//private static final String FRONTEND = "1";
	//private static final String SHOPEND = "2";
	
	@RequestMapping(value="logincheck", method=RequestMethod.GET)
	public String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("weixin login get...");
		//获取微信公众号传输过来的code 通过code可获取access_token，进而获取用户信息
		String code = request.getParameter("code");
		//这个state可以用来传我们自定义的信息，方便程序调用
		//String roleType = request.getParameter("state");
		log.debug("weixin login code :" + code);
		WechatUser user = null;
		String openId = null;
		//WeChatAuth auth = null;
		if(code != null) {
			UserAccessToken token;
			try {
				//通过code获取access_token
				token = WechatUtil.getUserAccessToken(code);
				log.debug("weixin login token:" + token.toString());
				//通过token获取access_token
				String accessToken = token.getAccessToken();
				//通过token获取openId
				openId = token.getOpenId();
				//通过token获取用户昵称等信息
				user = WechatUtil.getUserInfo(accessToken, openId);
				log.debug("wexin login user :" + user.toString());
				request.getSession().setAttribute("openId", openId);
				//TODO 注册用户信息时将下面注释取消
				//auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (Exception e) {
				log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
				e.printStackTrace();
			}
		}
		//TODO 若微信账号为空则需要注册微信账号，同时注册用户信息
		if(user != null) {
			return "frontend/index";
		}else {
			return null;
		}
	}
}
