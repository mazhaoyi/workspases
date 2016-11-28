package doordu.com.LogConsumer.vo;

import java.io.Serializable;
/**
 * 异步日志响应结果信息
 *
 */
public class NsyncLogResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -33451660703665197L;
	private boolean success;
	private String errorCode;
	private String errorMsg;
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
