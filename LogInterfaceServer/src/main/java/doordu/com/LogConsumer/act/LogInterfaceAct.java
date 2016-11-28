package doordu.com.LogConsumer.act;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doordu.com.LogConsumer.svc.IJfkProducerSvc;
import doordu.com.LogConsumer.vo.NsyncLogResponse;

@RestController
@RequestMapping(value = "/rest")
public class LogInterfaceAct {
	private static final Logger logger = LoggerFactory.getLogger(LogInterfaceAct.class);
	
	@Autowired
	private IJfkProducerSvc jfkProducerSvc;
	
	@RequestMapping(value = "/log", method = RequestMethod.POST)
	public NsyncLogResponse callLogInterface(@RequestParam String msg) {
		NsyncLogResponse resp = new NsyncLogResponse();
		try {
			jfkProducerSvc.sendMessage(msg);
		} catch (Exception e) {
			logger.error("error:{}", e);
			resp.setErrorCode(null);
			resp.setSuccess(false);
			resp.setErrorMsg(e.getMessage());
			return resp;
		}
		resp.setErrorCode(null);
		resp.setSuccess(true);
		resp.setErrorMsg(null);
		return resp;
	}
}
