package com.roncoo.education.web.boss.biz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 上传功能
 * 
 * @author wujing
 */
@Component
public class UploadBiz extends BaseBiz {

	@Autowired
	private IBossSys bossSys;

	public Object uploadPic(MultipartFile descFile) {
		String url = null;
		if (descFile != null && !descFile.isEmpty()) {
			url = AliyunUtil.uploadPic(PlatformEnum.COURSE, descFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class));
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("data", url);
		return map;
	}

}
