/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.aliyun;

import java.io.File;

import com.aliyun.oas.OASFactory;
import com.aliyun.oas.ease.ArchiveManager;
import com.aliyun.oas.ease.monitor.JobMonitor;
import com.aliyun.oas.model.common.ServiceCredentials;
import com.aliyun.oas.model.result.UploadResult;
import com.roncoo.education.util.config.ConfigUtil;

/**
 * 阿里云OAS工具类
 * 
 * @author wujing
 * 
 * @link https://help.aliyun.com/document_detail/27408.html?spm=5176.doc27385.6.579.6t8ten
 */
public final class AliyunOasUtil {

	private AliyunOasUtil() {
	}

	private static ArchiveManager archiveManager;

	/**
	 * 返回文件URl
	 * 
	 * @param platform
	 * @param file
	 * @param fileName
	 * @return
	 */
	public static String upload(File file) {
		// 上传文件
		UploadResult uploadResult = getArchiveManager().upload(ConfigUtil.ALIYUN_OAS_VAULT, file);
		return uploadResult.getArchiveId();
	}

	/**
	 * 下载
	 */
	public static void download(String archiveId, String filePath) {
		JobMonitor jobMonitor = getArchiveManager().downloadAsync(ConfigUtil.ALIYUN_OAS_VAULT, archiveId);
		jobMonitor.waitUntilFinished();
		getArchiveManager().downloadJobOutput(ConfigUtil.ALIYUN_OAS_VAULT, jobMonitor.getJobId(), new File(filePath));
	}

	private static ArchiveManager getArchiveManager() {
		if (archiveManager == null) {
			ServiceCredentials credentials = new ServiceCredentials(ConfigUtil.ALIYUN_ACCESSKEYID, ConfigUtil.ALIYUN_ACCESSKEYSECRET);
			archiveManager = OASFactory.archiveManagerFactory(credentials, ConfigUtil.ALIYUN_OAS_URL);
		}
		return archiveManager;
	}

}
