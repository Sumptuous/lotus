package com.lotus.core.service.user;

import com.lotus.core.bean.user.Buyer;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wyy
 */
public interface ProfileService {

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
    String signup(final Buyer buyer, String conPassword, String captcha, ModelMap model, HttpServletRequest request, HttpServletResponse response);

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
    String login(Buyer buyer, String captcha, String returnUrl, ModelMap model, HttpServletRequest request, HttpServletResponse response);

    /**
     * 个人资料
     * @param request
     * @param model
     * @param response
     * @return
     */
    String profile(HttpServletRequest request,ModelMap model,HttpServletResponse response);
}
