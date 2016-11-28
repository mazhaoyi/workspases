package doordu.com.LogConsumer.vo;

import java.util.Date;

public class DoorduLogVo {
	private Integer logId;

	private String logRequestIp;

	private String logRequestServerName;

	private String logRequestTitle;

	private Integer logRequestLineNumber;

	private Date logRequestTime;

	private String line1;

	private String line2;

	private String line3;

	private String line4;

	private String logRequestDesc;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getLogRequestIp() {
		return logRequestIp;
	}

	public void setLogRequestIp(String logRequestIp) {
		this.logRequestIp = logRequestIp == null ? null : logRequestIp.trim();
	}

	public String getLogRequestServerName() {
		return logRequestServerName;
	}

	public void setLogRequestServerName(String logRequestServerName) {
		this.logRequestServerName = logRequestServerName == null ? null : logRequestServerName.trim();
	}

	public String getLogRequestTitle() {
		return logRequestTitle;
	}

	public void setLogRequestTitle(String logRequestTitle) {
		this.logRequestTitle = logRequestTitle == null ? null : logRequestTitle.trim();
	}

	public Integer getLogRequestLineNumber() {
		return logRequestLineNumber;
	}

	public void setLogRequestLineNumber(Integer logRequestLineNumber) {
		this.logRequestLineNumber = logRequestLineNumber;
	}

	public Date getLogRequestTime() {
		return logRequestTime;
	}

	public void setLogRequestTime(Date logRequestTime) {
		this.logRequestTime = logRequestTime;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1 == null ? null : line1.trim();
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2 == null ? null : line2.trim();
	}

	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3 == null ? null : line3.trim();
	}

	public String getLine4() {
		return line4;
	}

	public void setLine4(String line4) {
		this.line4 = line4 == null ? null : line4.trim();
	}

	public String getLogRequestDesc() {
		return logRequestDesc;
	}

	public void setLogRequestDesc(String logRequestDesc) {
		this.logRequestDesc = logRequestDesc == null ? null : logRequestDesc.trim();
	}
}
