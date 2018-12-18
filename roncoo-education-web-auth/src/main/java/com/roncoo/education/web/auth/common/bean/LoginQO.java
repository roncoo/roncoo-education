/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.auth.common.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginQO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientId;

	private String responseType;

	private String redirectUri;

}
