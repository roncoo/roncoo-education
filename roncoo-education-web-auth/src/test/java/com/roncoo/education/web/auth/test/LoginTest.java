/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.auth.test;

import com.xiaoleilu.hutool.http.HttpUtil;

public class LoginTest {

	public static void main(String[] args) {
		String clienId = "lk55582071e069423a8210b5a195877af0";
		String redirectUri = "http://www.roncoo.com";
		String url1 = "http://localhost:9860/login?clientId={0}&responseType=code&redirectUri={1}".replace("{0}", clienId).replace("{1}", HttpUtil.encode(redirectUri, "utf-8"));
		String url2 = "http://192.168.1.185:9860/auth/login?clientId={0}&responseType=code&redirectUri={1}".replace("{0}", clienId).replace("{1}", HttpUtil.encode(redirectUri, "utf-8"));
		System.out.println(url1);
		System.out.println(url2);
	}

}
