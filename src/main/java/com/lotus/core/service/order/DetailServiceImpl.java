package com.lotus.core.service.order;
import com.lotus.common.page.Pagination;
import com.lotus.core.bean.order.Detail;
import com.lotus.core.dao.order.DetailMapper;
import com.lotus.core.query.order.DetailQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DetailServiceImpl implements DetailService {

	@Resource
	DetailMapper detailMapper;

	public Integer addDetail(Detail detail) {
		return detailMapper.addDetail(detail);
	}

	@Transactional(readOnly = true)
	public Detail getDetailByKey(Integer id) {
		return detailMapper.getDetailByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Detail> getDetailsByKeys(List<Integer> idList) {
		return detailMapper.getDetailsByKeys(idList);
	}

	public Integer deleteByKey(Integer id) {
		return detailMapper.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return detailMapper.deleteByKeys(idList);
	}

	public Integer updateDetailByKey(Detail detail) {
		return detailMapper.updateDetailByKey(detail);
	}
	
	@Transactional(readOnly = true)
	public Pagination getDetailListWithPage(DetailQuery detailQuery) {
		Pagination p = new Pagination(detailQuery.getPageNo(),detailQuery.getPageSize(),detailMapper.getDetailListCount(detailQuery));
		p.setList(detailMapper.getDetailListWithPage(detailQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Detail> getDetailList(DetailQuery detailQuery) {
		return detailMapper.getDetailList(detailQuery);
	}
}
