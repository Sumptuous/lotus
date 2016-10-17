package com.lotus.core.controller;

import com.lotus.common.email.EmailService;
import com.lotus.common.encode.Md5Pwd;
import com.lotus.common.web.ResponseUtils;
import com.lotus.common.web.session.SessionProvider;
import com.lotus.core.bean.country.City;
import com.lotus.core.bean.country.Province;
import com.lotus.core.bean.country.Town;
import com.lotus.core.bean.user.Buyer;
import com.lotus.core.query.country.CityQuery;
import com.lotus.core.query.country.TownQuery;
import com.lotus.core.service.country.CityService;
import com.lotus.core.service.country.ProvinceService;
import com.lotus.core.service.country.TownService;
import com.lotus.core.service.user.BuyerService;
import com.lotus.core.service.user.ProfileService;
import com.lotus.core.web.Constants;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 跳转登陆页面
 * 登陆
 * 个人资料
 * 收货地址
 * @author wyy
 *
 */
@Controller
public class ProfileController {
	
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private Md5Pwd md5Pwd;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private TownService townService;
	@Resource
	private ProfileService profileService;

	/**
	 * 跳转到注册页
	 * @return
     */
	@RequestMapping(value = "/shopping/signup.shtml", method = RequestMethod.GET)
	public String signup(){
		return "buyer/signup";
	}

	/**
	 * 用户注册
	 * @param buyer
	 * @param conPassword
	 * @param captcha
	 * @param model
	 * @param request
	 * @param response
     * @return
     */
	@RequestMapping(value = "/shopping/signup.shtml", method = RequestMethod.POST)
	public String signup(Buyer buyer, String conPassword, String captcha, ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return profileService.signup(buyer, conPassword, captcha, model, request, response);
	}

	/**
	 * 跳转到登录页
	 * @return
     */
	@RequestMapping(value = "/shopping/login.shtml",method=RequestMethod.GET)
	public String login(){
		return "buyer/login";
	}

	/**
	 * 用户登录
	 * @param buyer
	 * @param captcha
	 * @param returnUrl
	 * @param model
	 * @param request
	 * @param response
     * @return
     */
	@RequestMapping(value = "/shopping/login.shtml",method=RequestMethod.POST)
	public String login(Buyer buyer, String captcha, String returnUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return profileService.login(buyer, captcha, returnUrl, model, request, response);
	}

	/**
	 * 个人中心
	 * @return
     */
	@RequestMapping(value = "/buyer/index.shtml")
	public String index(){
		return "buyer/index";
	}

	/**
	 * 个人资料
	 * @param request
	 * @param model
	 * @param response
     * @return
     */
	@RequestMapping(value = "/buyer/profile.shtml")
	public String profile(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		return profileService.profile(request, model, response);
	}

	@RequestMapping(value = "/buyer/city.shtml")
	public void city(String code,HttpServletResponse response){
		CityQuery cityQuery = new CityQuery();
		cityQuery.setProvince(code);
		List<City> citys = cityService.getCityList(cityQuery);
		
		JSONObject jo = new JSONObject();
		jo.put("citys", citys);
		ResponseUtils.renderJson(response, jo.toString());
		
	}

	/**
	 * 收货地址
	 * @return
     */
	@RequestMapping(value = "/buyer/deliver_address.shtml")
	public String address(){
		
		return "buyer/deliver_address";
	}
}
