/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.base;

import java.io.Serializable;

public class Bjui implements Serializable {
	private static final long serialVersionUID = 486427439928047616L;

	private int statusCode; // 必选。状态码
	private String message; // 可选。信息内容。
	private String tabid; // 可选。
	private String dialogid; // 可选。
	private String divid; // 可选。
	private boolean closeCurrent; // 可选
	private String forward; // 可选
	private String forwardConfirm; // 可选

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTabid() {
		return tabid;
	}

	public void setTabid(String tabid) {
		this.tabid = tabid;
	}

	public String getDialogid() {
		return dialogid;
	}

	public void setDialogid(String dialogid) {
		this.dialogid = dialogid;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public boolean isCloseCurrent() {
		return closeCurrent;
	}

	public void setCloseCurrent(boolean closeCurrent) {
		this.closeCurrent = closeCurrent;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getForwardConfirm() {
		return forwardConfirm;
	}

	public void setForwardConfirm(String forwardConfirm) {
		this.forwardConfirm = forwardConfirm;
	}

	@Override
	public String toString() {
		return "Bjui [statusCode=" + statusCode + ", message=" + message + ", tabid=" + tabid + ", dialogid=" + dialogid + ", divid=" + divid + ", closeCurrent=" + closeCurrent + ", forward=" + forward + ", forwardConfirm=" + forwardConfirm + "]";
	}

}
