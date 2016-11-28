package test.svc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import doordu.com.entity.DoorduLogEntity;
import doordu.com.svc.IDoorduLogSvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@Transactional
public class MybatisTest {
	private static final Logger logger = Logger.getLogger(MybatisTest.class);

	@Autowired
	private IDoorduLogSvc doorduLogSvc;

	@Test
	public void test() {
		logger.info("start");

		List<DoorduLogEntity> records = new ArrayList<DoorduLogEntity>();
		for (int i = 0; i < 100000; i++) {
			DoorduLogEntity de = new DoorduLogEntity();
			de.setLogId(null);
			de.setLogRequestIp("127.0.0.1");
			de.setLogRequestServerName("email server " + i);
			de.setLogRequestTime(new Date());
			de.setLogRequestLineNumber(i);
			de.setLogRequestTitle("this is test " + i);
			de.setLogRequestDesc("Sorry i");
			records.add(de);
		}

		long start = Calendar.getInstance().getTimeInMillis();
		int res = doorduLogSvc.insertList(records);
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println((end - start) / 1000 + "s");
		System.out.println(res);
	}
}
