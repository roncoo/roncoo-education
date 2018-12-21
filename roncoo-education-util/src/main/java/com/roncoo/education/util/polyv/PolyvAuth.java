/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.polyv;

import java.io.Serializable;

public class PolyvAuth implements Serializable {

	private String vid;
	private String t;
	private String code;
	private String callback;
	private String secretkey;

	private static final long serialVersionUID = 1L;

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
	
	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	@Override
	public String toString() {
		return "PolyvAuth [vid=" + vid + ", t=" + t + ", code=" + code + ", callback=" + callback + ", secretkey=" + secretkey + "]";
	}

}
