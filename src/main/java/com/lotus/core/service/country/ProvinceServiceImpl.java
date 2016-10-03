package com.lotus.core.service.country;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.country.Province;
import com.lotus.core.dao.country.ProvinceMapper;
import com.lotus.core.query.country.ProvinceQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

	@Resource
	ProvinceMapper provinceMapper;

	public Integer addProvince(Province province) {
		return provinceMapper.addProvince(province);
	}

	@Transactional(readOnly = true)
	public Province getProvinceByKey(Integer id) {
		return provinceMapper.getProvinceByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Province> getProvincesByKeys(List<Integer> idList) {
		return provinceMapper.getProvincesByKeys(idList);
	}

	public Integer deleteByKey(Integer id) {
		return provinceMapper.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return provinceMapper.deleteByKeys(idList);
	}

	public Integer updateProvinceByKey(Province province) {
		return provinceMapper.updateProvinceByKey(province);
	}
	
	@Transactional(readOnly = true)
	public Pagination getProvinceListWithPage(ProvinceQuery provinceQuery) {
		Pagination p = new Pagination(provinceQuery.getPageNo(),provinceQuery.getPageSize(),provinceMapper.getProvinceListCount(provinceQuery));
		p.setList(provinceMapper.getProvinceListWithPage(provinceQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Province> getProvinceList(ProvinceQuery provinceQuery) {
		return provinceMapper.getProvinceList(provinceQuery);
	}
}
