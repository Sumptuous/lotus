package com.lotus.core.controller;
import com.lotus.common.web.session.SessionProvider;
import com.lotus.core.bean.BuyCart;
import com.lotus.core.bean.BuyItem;
import com.lotus.core.bean.order.Order;
import com.lotus.core.bean.product.Sku;
import com.lotus.core.bean.user.Buyer;
import com.lotus.core.service.order.OrderService;
import com.lotus.core.service.product.SkuService;
import com.lotus.core.web.Constants;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 提交订单  前台
 * @author wyy
 *
 */
@Controller
public class FrontOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private SkuService skuService;
	
	//提交订单
	@RequestMapping(value = "/buyer/confirmOrder.shtml")
	public String confirmOrder(Order order, HttpServletRequest request, HttpServletResponse response){
		//1:接收前台传四个参数
		
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Inclusion.NON_NULL);
		
		//声明
		BuyCart buyCart = new BuyCart();
		//判断Cookie是否有购物车  
		
		//JESSIONID
		//buyCart_cookie
		//  
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length >0){
			for(Cookie c : cookies){
				if(Constants.BUYCART_COOKIE.equals(c.getName())){
					//如果有了  就使用此购物车
					String value = c.getValue();//
					//
					try {
						buyCart = om.readValue(value, BuyCart.class);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		//装购物车装满
		List<BuyItem> its = buyCart.getItems();
		for(BuyItem item : its){
			Sku s = skuService.getSkuByKey(item.getSku().getId());
			item.setSku(s);
			//小计
		}
		
		Buyer buyer = (Buyer) sessionProvider.getAttribute(request, response, Constants.BUYER_SESSION);
		//用户Id
		order.setBuyerId(buyer.getUsername());
		//保存订单   订单详情  二张表
		orderService.addOrder(order,buyCart);
		//清空购物车
		Cookie cookie = new Cookie(Constants.BUYCART_COOKIE,null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return "product/confirmOrder";
	}
	
}
