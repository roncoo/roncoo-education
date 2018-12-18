package com.roncoo.education.web.boss.biz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.util.aliyun.AliyunOssUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.enums.PlatformEnum;

/**
 * 上传功能
 * 
 * @author wujing
 */
@Component
public class UploadBiz extends BaseBiz {

	public Object uploadPic(MultipartFile descFile) {
		String url = null;
		if (descFile != null && !descFile.isEmpty()) {
			url = AliyunOssUtil.uploadPic(PlatformEnum.COURSE, descFile);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("data", url);
		return map;
	}

}
