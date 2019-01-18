/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api.callback;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.interfaces.gateway.callback.CallbackPolyv;
import com.roncoo.education.course.service.biz.callback.CallbackPolyvBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.polyv.PolyvAuth;
import com.roncoo.education.util.polyv.PolyvVideo;

/**
 * 课时信息-审核
 *
 * @author wujing123
 */
@RestController
public class CallbackPolyvController extends BaseController implements CallbackPolyv {

	@Autowired
	private CallbackPolyvBiz biz;
	
	@Override
	public String callbackPolyvVideo(PolyvVideo polyvVideo) {
		return biz.video(polyvVideo);
	}

	@Override
	public String callbackPolyvAuth(PolyvAuth polyvAuth, HttpServletRequest request) {
		if (StringUtils.isEmpty(polyvAuth.getCallback())) {
			String sourceParam = request.getQueryString();
			sourceParam = sourceParam.replaceAll("vid=" + polyvAuth.getVid(), "");
			sourceParam = sourceParam.replaceAll("&t=" + polyvAuth.getT(), "");
			sourceParam = sourceParam.replaceAll("&code=", "").replace("+", "%2B");
			polyvAuth.setCode(sourceParam);
		}
		return biz.auth(polyvAuth);
	}

}
