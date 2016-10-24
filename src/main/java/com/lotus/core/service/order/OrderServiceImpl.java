package com.lotus.core.service.order;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.BuyCart;
import com.lotus.core.bean.BuyItem;
import com.lotus.core.bean.order.Detail;
import com.lotus.core.bean.order.Order;
import com.lotus.core.dao.order.OrderMapper;
import com.lotus.core.query.order.OrderQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Resource
	OrderMapper orderMapper;
	@Resource
	DetailService detailService;

	public Integer addOrder(Order order, BuyCart buyCart) {
		//生成订单号
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String oid = df.format(new Date());
		order.setOid(oid);
		//运费
		order.setDeliverFee(buyCart.getFee());
		//应付金额
		order.setPayableFee(buyCart.getTotalPrice());
		//订单金额
		order.setTotalPrice(buyCart.getProductPrice());
		//支付状态
		if(order.getPaymentWay() == 0){
			order.setIsPay(0);
		}else{
			order.setIsPay(1);
		}
		//订单状态   提交订单
		order.setState(0);
		//订单生成时间 
		order.setCreateDate(new Date());
		//保存订单
		Integer o = orderMapper.addOrder(order);
		//购物项集合
		List<BuyItem> items = buyCart.getItems();
		
		for(BuyItem item :items){
			//保存订单详情  List集合 
			Detail detail = new Detail();
			//设置一个订单ID
			detail.setOrderId(order.getId());
			//item.sku.product.name
			//商品名称
			detail.setProductName(item.getSku().getProduct().getName());
			//商品编号
			detail.setProductNo(item.getSku().getProduct().getNo());
			//颜色名称
			detail.setColor(item.getSku().getColor().getName());
			//尺码 
			detail.setSize(item.getSku().getSize());
			//商品销售价
			detail.setSkuPrice(item.getSku().getSkuPrice());
			//购物数量
			detail.setAmount(item.getAmount());
			//保存
			detailService.addDetail(detail);
		}
		
		return o;
	}

	@Transactional(readOnly = true)
	public Order getOrderByKey(Integer id) {
		return orderMapper.getOrderByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Order> getOrdersByKeys(List<Integer> idList) {
		return orderMapper.getOrdersByKeys(idList);
	}

	public Integer deleteByKey(Integer id) {
		return orderMapper.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return orderMapper.deleteByKeys(idList);
	}

	public Integer updateOrderByKey(Order order) {
		return orderMapper.updateOrderByKey(order);
	}
	
	@Transactional(readOnly = true)
	public Pagination getOrderListWithPage(OrderQuery orderQuery) {
		Pagination p = new Pagination(orderQuery.getPageNo(),orderQuery.getPageSize(),orderMapper.getOrderListCount(orderQuery));
		p.setList(orderMapper.getOrderListWithPage(orderQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Order> getOrderList(OrderQuery orderQuery) {
		return orderMapper.getOrderList(orderQuery);
	}
}
