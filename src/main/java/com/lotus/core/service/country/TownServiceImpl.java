package com.lotus.core.service.country;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.country.Town;
import com.lotus.core.dao.country.TownMapper;
import com.lotus.core.query.country.TownQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {

	@Resource
	TownMapper townMapper;

	public Integer addTown(Town town) {
		return townMapper.addTown(town);
	}

	@Transactional(readOnly = true)
	public Town getTownByKey(Integer id) {
		return townMapper.getTownByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Town> getTownsByKeys(List<Integer> idList) {
		return townMapper.getTownsByKeys(idList);
	}

	public Integer deleteByKey(Integer id) {
		return townMapper.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return townMapper.deleteByKeys(idList);
	}

	public Integer updateTownByKey(Town town) {
		return townMapper.updateTownByKey(town);
	}
	
	@Transactional(readOnly = true)
	public Pagination getTownListWithPage(TownQuery townQuery) {
		Pagination p = new Pagination(townQuery.getPageNo(),townQuery.getPageSize(),townMapper.getTownListCount(townQuery));
		p.setList(townMapper.getTownListWithPage(townQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Town> getTownList(TownQuery townQuery) {
		return townMapper.getTownList(townQuery);
	}
}
