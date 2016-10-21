package com.lotus.core.service.user;

import com.lotus.common.email.EmailService;
import com.lotus.common.encode.Md5Pwd;
import com.lotus.common.exception.BaseException;
import com.lotus.common.exception.ErrorCode;
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
import com.lotus.core.sys.CurrentContext;
import com.lotus.core.sys.model.CurrentBuyer;
import com.lotus.core.web.Constants;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @author wyy
 */

@Service
public class ProfileServiceImpl implements ProfileService {

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
    private EmailService emailService;
    @Value("${email.createAccount}")
    private String emailCreateAccount;

    public String register(final Buyer buyer, String conPassword, String captcha, ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        if (!StringUtils.isNotBlank(buyer.getUsername())) {
            throw new BaseException(ErrorCode.PLEASE_INPUT_USERNAME);
        }
        if(!StringUtils.isNotBlank(captcha)){
            throw new BaseException(ErrorCode.PLEASE_INPUT_VERIFICATION_CODE);
        }
        if (!StringUtils.isNotBlank(buyer.getPassword()) && !StringUtils.isNotBlank(conPassword)) {
            throw new BaseException(ErrorCode.PLEASE_INPUT_PASSWORD);
        }
        if (!imageCaptchaService.validateResponseForID(sessionProvider.getSessionId(request, response), captcha)) {
            throw new BaseException(ErrorCode.INCORRECT_VERIFICATION_CODE);
        }
        if (!conPassword.equals(buyer.getPassword())) {
            throw new BaseException(ErrorCode.PASSWORD_NOT_MATCH);
        }
        if (buyer.getUsername().matches("^[a-zA-Z\\d_\\.]+$") || buyer.getUsername().length()>6 || buyer.getUsername().length()<16){
            throw new BaseException(ErrorCode.USERNAME_NOT_VALID);
        }

        Buyer b = buyerService.getBuyerByKey(buyer.getUsername());
        //判断用户存不存在
        if (b != null){
            throw new BaseException(ErrorCode.USERNAME_ALREADY_EXIST);
        }

        b = new Buyer();
        String password = md5Pwd.encode(buyer.getPassword());
        b.setUsername(buyer.getUsername());
        b.setPassword(password);
        buyerService.addBuyer(b);

        //给用户发送邮件
        if (!StringUtils.isNotBlank(buyer.getEmail())) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    HashMap<String, Object> content = new HashMap<String, Object>();
                    content.put("username", buyer.getUsername());
                    emailService.sendEmail(buyer.getEmail(), "ganwu13@163.com", emailCreateAccount, "createAccount", content);
                }
            };
            thread.start();
        }
        return "redirect:/shopping/login.shtml";
    }

    public String login(Buyer buyer, String captcha, String returnUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.isNotBlank(buyer.getUsername())) {
            throw new BaseException(ErrorCode.PLEASE_INPUT_USERNAME);
        }
        if(!StringUtils.isNotBlank(captcha)){
            throw new BaseException(ErrorCode.PLEASE_INPUT_VERIFICATION_CODE);
        }
        if (!StringUtils.isNotBlank(buyer.getPassword())) {
            throw new BaseException(ErrorCode.PLEASE_INPUT_PASSWORD);
        }
        if (!imageCaptchaService.validateResponseForID(sessionProvider.getSessionId(request, response), captcha)) {
            throw new BaseException(ErrorCode.INCORRECT_VERIFICATION_CODE);
        }
//        if (buyer.getPassword().matches("[a-zA-Z\\d\\.]+") || buyer.getPassword().length()>6 || buyer.getPassword().length()<16){
//            throw new BaseException(ErrorCode.PASSWORD_NOT_VALID);
//        }

        CurrentBuyer b = buyerService.getBuyerByKey(buyer.getUsername());
        //判断用户存不存在
        if (b == null){
            throw new BaseException(ErrorCode.USERNAME_NOT_EXIST);
        }
        if(!b.getPassword().equals(md5Pwd.encode(buyer.getPassword()))){
            throw new BaseException(ErrorCode.INCORRECT_PASSWORD);
        }

        //把用户对象放在Session
        sessionProvider.setAttribute(request,response, Constants.BUYER_SESSION, b);

        //把用户对象放在线程变量中
        CurrentContext.set(b);
        if(StringUtils.isNotBlank(returnUrl)){
            return "redirect:" + returnUrl;
        }else{
            //个人中心
            return "redirect:/buyer/index.shtml" ;
        }
    }

    public String profile(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
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

    public String logout(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
        sessionProvider.logout(request, response);
        return "redirect:" + returnUrl;
    }

    private void checkParam(String param, ModelMap model){
        if (param.matches("^[a-zA-Z\\d_\\.]+$")){

        }else {
            model.addAttribute("error", "");
        }
    }
}
