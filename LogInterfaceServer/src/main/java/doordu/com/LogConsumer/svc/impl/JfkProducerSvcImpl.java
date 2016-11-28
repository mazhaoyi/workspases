package doordu.com.LogConsumer.svc.impl;

import java.util.Properties;

import org.springframework.stereotype.Service;

import com.sohu.jafka.producer.Producer;
import com.sohu.jafka.producer.ProducerConfig;
import com.sohu.jafka.producer.StringProducerData;
import com.sohu.jafka.producer.serializer.StringEncoder;

import doordu.com.LogConsumer.svc.IJfkProducerSvc;
import doordu.com.LogConsumer.util.PropertiesUtils;

@Service
public class JfkProducerSvcImpl implements IJfkProducerSvc {

	@Override
	public void sendMessage(String msg) throws Exception {
		Properties props = new Properties();
//		props.setProperty("broker.list", "0:10.0.0.245:9092");
		props.setProperty("zk.connect", PropertiesUtils.get("common-config", "zk.url"));
		props.setProperty("serializer.class", StringEncoder.class.getName());
		props.setProperty("zk.sessiontimeout.ms", "6000");
		props.setProperty("zk.connectiontimeout.ms", "6000");
		
		ProducerConfig pc = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(pc);
		
		String topic = PropertiesUtils.get("common-config", "jfk.topic");
		StringProducerData spd = new StringProducerData(topic);
		
		spd.add(msg);
		try {
			producer.send(spd);
		} finally {
			producer.close();
		}
	}
}
