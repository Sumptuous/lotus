package com.lotus.core.controller.admin;
import com.lotus.core.bean.order.Detail;
import com.lotus.core.bean.order.Order;
import com.lotus.core.bean.user.Addr;
import com.lotus.core.query.order.DetailQuery;
import com.lotus.core.query.order.OrderQuery;
import com.lotus.core.query.user.AddrQuery;
import com.lotus.core.service.order.DetailService;
import com.lotus.core.service.order.OrderService;
import com.lotus.core.service.user.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 订单列表
 * 订单查看
 * @author wyy
 *
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private DetailService detailService;
	@Autowired
	private AddrService addrService;

	/**
	 * 订单列表
	 * @param isPay
	 * @param state
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/order/list.do")
	public String list(Integer isPay,Integer state,ModelMap model){
		
		OrderQuery orderQuery = new OrderQuery();
		//支付状态
		if(null != isPay){
			orderQuery.setIsPay(isPay);
		}
		//订单状态
		if(null != state){
			orderQuery.setState(state);
		}
		
		List<Order> orders = orderService.getOrderList(orderQuery);
		
		model.addAttribute("orders", orders);
		
		return "order/list";
	}

	/**
	 * 订单查看
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/order/view.do")
	public String view(Integer id,ModelMap model){
		//查询订单主表
		Order order = orderService.getOrderByKey(id);
		//查询订单详情表
		DetailQuery detailQuery = new DetailQuery();
		detailQuery.setOrderId(id);
		List<Detail> details = detailService.getDetailList(detailQuery);
		
		//收货地址
		AddrQuery addrQuery = new AddrQuery();
		addrQuery.setBuyerId(order.getBuyerId());
		addrQuery.setIsDef(1);
		
		List<Addr> addrs = addrService.getAddrList(addrQuery);
		
		//
		model.addAttribute("order", order);
		model.addAttribute("addr", addrs.get(0));
		model.addAttribute("details", details);
		
		return "order/view";
	}
}
