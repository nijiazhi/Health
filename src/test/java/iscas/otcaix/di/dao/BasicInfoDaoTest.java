package iscas.otcaix.di.dao;


import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-servlet.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BasicInfoDaoTest {
	/*@Autowired
	private IBasicInfoDao basicInfoDao;

	@Test
	public void testFind() {
		BasicInfo bi=basicInfoDao.find("LSQ");
		basicInfoDao.delete(bi);
		assertNotNull(bi);
	}*/

}
