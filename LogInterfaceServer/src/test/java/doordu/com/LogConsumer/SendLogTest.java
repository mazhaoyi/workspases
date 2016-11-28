package doordu.com.LogConsumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

import doordu.com.LogConsumer.act.LogInterfaceAct;
import doordu.com.LogConsumer.vo.DoorduLogVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml", "classpath:spring/spring-mvc.xml"})
public class SendLogTest {
	@Autowired
	private LogInterfaceAct logInterfaceAct;
	
	@Test
	public void sendLog() {
		Gson gson = new Gson();
		DoorduLogVo log = new DoorduLogVo();
		log.setLogRequestIp("127.0.0.1");
		log.setLogRequestServerName("localhost server");
		log.setLogRequestTitle("门禁日志");
		log.setLogRequestDesc("门禁广告不播放了！");
		String msg = gson.toJson(log);
		System.out.println(msg);
		logInterfaceAct.callLogInterface(msg);
	}
}
