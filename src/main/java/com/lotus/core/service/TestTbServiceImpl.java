package com.lotus.core.service;

import javax.annotation.Resource;

import com.lotus.core.bean.TestTb;
import com.lotus.core.dao.TestTbDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author wyy
 *
 */
@Service
@Transactional
public class TestTbServiceImpl implements TestTbService{

	@Resource
	private TestTbDao testTbDao;
	
	public void addTestTb(TestTb testTb) {
		testTbDao.addTestTb(testTb);

		throw new RuntimeException();
	}

}
