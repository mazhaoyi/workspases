package doordu.com.act;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sohu.jafka.consumer.Consumer;
import com.sohu.jafka.consumer.ConsumerConfig;
import com.sohu.jafka.consumer.ConsumerConnector;
import com.sohu.jafka.consumer.MessageStream;
import com.sohu.jafka.producer.serializer.StringDecoder;
import com.sohu.jafka.utils.ImmutableMap;

import doordu.com.entity.DoorduLogEntity;
import doordu.com.svc.IDoorduLogSvc;
import doordu.com.util.PropertiesUtils;

@Service
public class JafkaClientAct {
	@Autowired
	private IDoorduLogSvc doorduLogSvc;
	
	public void init() {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(new Runnable() {
			public void run() {
				try {
					consumerLogData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		JafkaClientAct j = new JafkaClientAct();
		try {
			j.consumerLogData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void consumerLogData() throws Exception {
		Properties props = new Properties();
		// zookeeper url
		props.setProperty("zk.connect", PropertiesUtils.get("common-config", "zk.url"));
		// jafka consumer groupid
		props.setProperty("groupid", "1");
		props.setProperty("zk.sessiontimeout.ms", PropertiesUtils.get("common-config", "zk.sessiontimeout"));
		props.setProperty("zk.connectiontimeout.ms", PropertiesUtils.get("common-config", "zk.connectiontimeout"));
		
		ConsumerConfig config = new ConsumerConfig(props);
		ConsumerConnector connector = Consumer.create(config);
		
		String jfkTopic = PropertiesUtils.get("common-config", "jfk.topic");
		
		Map<String, List<MessageStream<String>>> topicMessageStreamsMap = connector.createMessageStreams(ImmutableMap.of(jfkTopic, 2), new StringDecoder());
		List<MessageStream<String>> topicMessageStreams = topicMessageStreamsMap.get(jfkTopic);
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		for (final MessageStream<String> ms : topicMessageStreams) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					try {
//						List<DoorduLogEntity> list = new ArrayList<DoorduLogEntity>();
						Gson gson = new Gson();
						for (String m : ms) {
							try {
								System.out.println(m + "-------------------------->");
								DoorduLogEntity dle = gson.fromJson(m, new TypeToken<DoorduLogEntity>(){}.getType());
								doorduLogSvc.insert(dle);
//								list.add(dle);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
//						doorduLogSvc.insertList(list);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
}
