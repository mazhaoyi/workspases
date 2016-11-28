package doordu.com.LogConsumer.util;

import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public class PropertiesUtils {
	public static String get(String path, String key) throws Exception {
		if (StringUtils.isBlank(path) || StringUtils.isBlank(key)) {
			throw new Exception("path or key cannot be blank !");
		}
		ResourceBundle rb = ResourceBundle.getBundle(path);
		if (rb == null) {
			throw new Exception("resource doesn't exsists at the path : " + path);
		}
		return rb.getString(key);
	}
}
