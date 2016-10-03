package com.lotus.core.service.country;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.country.City;
import com.lotus.core.dao.country.CityMapper;
import com.lotus.core.query.country.CityQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Resource
	CityMapper cityMapper;

	public Integer addCity(City city) {
		return cityMapper.addCity(city);
	}

	@Transactional(readOnly = true)
	public City getCityByKey(Integer id) {
		return cityMapper.getCityByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<City> getCitysByKeys(List<Integer> idList) {
		return cityMapper.getCitysByKeys(idList);
	}

	public Integer deleteByKey(Integer id) {
		return cityMapper.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return cityMapper.deleteByKeys(idList);
	}

	public Integer updateCityByKey(City city) {
		return cityMapper.updateCityByKey(city);
	}
	
	@Transactional(readOnly = true)
	public Pagination getCityListWithPage(CityQuery cityQuery) {
		Pagination p = new Pagination(cityQuery.getPageNo(),cityQuery.getPageSize(),cityMapper.getCityListCount(cityQuery));
		p.setList(cityMapper.getCityListWithPage(cityQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<City> getCityList(CityQuery cityQuery) {
		return cityMapper.getCityList(cityQuery);
	}
}
