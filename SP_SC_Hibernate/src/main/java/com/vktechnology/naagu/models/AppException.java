package com.vktechnology.naagu.models;

import java.io.Serializable;

public class AppException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;
	private Object printStackMsg;

	public Object getPrintStackMsg() {
		return printStackMsg;
	}

	public void setPrintStackMsg(Object printStackMsg) {
		this.printStackMsg = printStackMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public AppException(String errCode, String errMsg, Object printStackMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.printStackMsg = printStackMsg;
	}


}
