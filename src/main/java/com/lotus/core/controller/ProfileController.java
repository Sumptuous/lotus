package com.lotus.core.controller;

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
import com.lotus.core.web.Constants;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	//GET
	@RequestMapping(value = "/shopping/login.shtml",method=RequestMethod.GET)
	public String login(){
		return "buyer/login";
	}
	//POST
	/**
	 * 1:验证码是否为null
		2:验证码  是否正确
		3:用户是否为NUll
		4:密码是否为NUll
		5:用户是否正确
		6密码是否正确
		放进Session   跳转ReturnUrl
	 * @param buyer
	 * @param captcha
	 * @param returnUrl
	 * @return
	 */
	@RequestMapping(value = "/shopping/login.shtml",method=RequestMethod.POST)
	public String login(Buyer buyer, String captcha, String returnUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response){
		//验证码是否为null
		if(StringUtils.isNotBlank(captcha)){
			//1:JSESSIONID
			//2验证码
			if(imageCaptchaService.validateResponseForID(sessionProvider.getSessionId(request,response), captcha)){
				if(null != buyer && StringUtils.isNotBlank(buyer.getUsername())){
					if(StringUtils.isNotBlank(buyer.getPassword())){
						Buyer b = buyerService.getBuyerByKey(buyer.getUsername());
						if(null != b){
							//
							if(b.getPassword().equals(md5Pwd.encode(buyer.getPassword()))){
								//把用户对象放在Session
								sessionProvider.setAttribute(request,response, Constants.BUYER_SESSION, b);
								if(StringUtils.isNotBlank(returnUrl)){
									return "redirect:" + returnUrl;
								}else{
									//个人中心
									return "redirect:/buyer/index.shtml" ;
								}
							}else{
								model.addAttribute("error", "密码错误");
							}
						}else{
							model.addAttribute("error", "用户名输入错误");
						}
					}else{
						model.addAttribute("error", "请输入密码");
					}
				}else{
					model.addAttribute("error", "请输入用户名");
				}
			}else{
				model.addAttribute("error", "验证码输入错误");
			}
		}else{
			model.addAttribute("error", "请填写验证码");
		}
		return "buyer/login";
	}
	//个人中心
	@RequestMapping(value = "/buyer/index.shtml")
	public String index(){
		return "buyer/index";
	}
	//个人资料
	@RequestMapping(value = "/buyer/profile.shtml")
	public String profile(HttpServletRequest request,ModelMap model,HttpServletResponse response){
		//加载用户
		Buyer buyer = (Buyer) sessionProvider.getAttribute(request,response, Constants.BUYER_SESSION);
		Buyer b = buyerService.getBuyerByKey(buyer.getUsername());
		model.addAttribute("buyer", b);
		//省
		List<Province> provinces = provinceService.getProvinceList(null);
		model.addAttribute("provinces", provinces);
		//市 
		CityQuery cityQuery = new CityQuery();
		cityQuery.setProvince(b.getProvince());
		List<City> citys = cityService.getCityList(cityQuery);
		model.addAttribute("citys", citys);
		//县
		TownQuery townQuery = new TownQuery();
		townQuery.setCity(b.getCity());
		List<Town> towns = townService.getTownList(townQuery);
		model.addAttribute("towns", towns);
		
		return "buyer/profile";
	}
	//
	@RequestMapping(value = "/buyer/city.shtml")
	public void city(String code,HttpServletResponse response){
		CityQuery cityQuery = new CityQuery();
		cityQuery.setProvince(code);
		List<City> citys = cityService.getCityList(cityQuery);
		
		JSONObject jo = new JSONObject();
		jo.put("citys", citys);
		ResponseUtils.renderJson(response, jo.toString());
		
	}
	//收货地址
	@RequestMapping(value = "/buyer/deliver_address.shtml")
	public String address(){
		
		return "buyer/deliver_address";
	}
}
