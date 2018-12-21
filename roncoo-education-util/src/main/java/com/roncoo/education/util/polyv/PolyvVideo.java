/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.polyv;

import java.io.Serializable;

public class PolyvVideo implements Serializable {

	private String vid;

	private String sign;

	private String type;
	
	private String secretkey;

	private static final long serialVersionUID = 1L;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getSecretkey() {
		return secretkey;
	}
	
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	@Override
	public String toString() {
		return "PolyvVideo [vid=" + vid + ", sign=" + sign + ", type=" + type + ", secretkey=" + secretkey + "]";
	}

}
