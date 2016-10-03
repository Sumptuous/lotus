package com.lotus.core.service.user;
import com.lotus.common.page.Pagination;
import com.lotus.core.bean.user.Addr;
import com.lotus.core.dao.user.AddrMapper;
import com.lotus.core.query.user.AddrQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AddrServiceImpl implements AddrService {

	@Resource
	AddrMapper addrMapper;

	public Integer addAddr(Addr addr) {
		return addrMapper.addAddr(addr);
	}

	@Transactional(readOnly = true)
	public Addr getAddrByKey(Integer id) {
		return addrMapper.getAddrByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Addr> getAddrsByKeys(List<Integer> idList) {
		return addrMapper.getAddrsByKeys(idList);
	}

	public Integer deleteByKey(Integer id) {
		return addrMapper.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return addrMapper.deleteByKeys(idList);
	}

	public Integer updateAddrByKey(Addr addr) {
		return addrMapper.updateAddrByKey(addr);
	}
	
	@Transactional(readOnly = true)
	public Pagination getAddrListWithPage(AddrQuery addrQuery) {
		Pagination p = new Pagination(addrQuery.getPageNo(),addrQuery.getPageSize(),addrMapper.getAddrListCount(addrQuery));
		p.setList(addrMapper.getAddrListWithPage(addrQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Addr> getAddrList(AddrQuery addrQuery) {
		return addrMapper.getAddrList(addrQuery);
	}
}
