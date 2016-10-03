package com.lotus.core.service.user;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.user.Buyer;
import com.lotus.core.dao.user.BuyerMapper;
import com.lotus.core.query.user.BuyerQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Resource
	BuyerMapper buyerMapper;

	public Integer addBuyer(Buyer buyer) {
		return buyerMapper.addBuyer(buyer);
	}

	@Transactional(readOnly = true)
	public Buyer getBuyerByKey(String id) {
		return buyerMapper.getBuyerByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Buyer> getBuyersByKeys(List<String> idList) {
		return buyerMapper.getBuyersByKeys(idList);
	}

	public Integer deleteByKey(String id) {
		return buyerMapper.deleteByKey(id);
	}

	public Integer deleteByKeys(List<String> idList) {
		return buyerMapper.deleteByKeys(idList);
	}

	public Integer updateBuyerByKey(Buyer buyer) {
		return buyerMapper.updateBuyerByKey(buyer);
	}
	
	@Transactional(readOnly = true)
	public Pagination getBuyerListWithPage(BuyerQuery buyerQuery) {
		Pagination p = new Pagination(buyerQuery.getPageNo(),buyerQuery.getPageSize(),buyerMapper.getBuyerListCount(buyerQuery));
		p.setList(buyerMapper.getBuyerListWithPage(buyerQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Buyer> getBuyerList(BuyerQuery buyerQuery) {
		return buyerMapper.getBuyerList(buyerQuery);
	}
}
