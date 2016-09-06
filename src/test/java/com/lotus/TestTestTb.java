package com.lotus;

import com.lotus.common.junit.SpringJunitTest;
import com.lotus.core.bean.TestTb;
import com.lotus.core.service.TestTbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 测试
 * @author wyy
 *
 */

public class TestTestTb extends SpringJunitTest {

	@Autowired
	private TestTbService testTbService;
	@Test
	public void testAdd() throws Exception {
		TestTb testTb = new TestTb();
		testTb.setName("金乐乐");
		
		testTbService.addTestTb(testTb);
	}
}
