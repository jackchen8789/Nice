package nice.exception;

import java.util.Date;

public class NiceExceptionResponse {

	private Date errTime;
	private String errMsg;
	private String errDetails;
	
	public NiceExceptionResponse(Date errTime, String errMsg, String errDetails) {
		super();
		this.errTime = errTime;
		this.errMsg = errMsg;
		this.errDetails = errDetails;
	}

	/**
	 * @return the errTime
	 */
	public Date getErrTime() {
		return errTime;
	}

	/**
	 * @param errTime the errTime to set
	 */
	public void setErrTime(Date errTime) {
		this.errTime = errTime;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * @return the errDetails
	 */
	public String getErrDetails() {
		return errDetails;
	}

	/**
	 * @param errDetails the errDetails to set
	 */
	public void setErrDetails(String errDetails) {
		this.errDetails = errDetails;
	}
	
	
	
	
}
