package com.roncoo.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.common.bean.qo.AdvQO;
import com.roncoo.education.course.common.bean.vo.AdvVO;
import com.roncoo.education.course.feign.web.IBossAdv;
import com.roncoo.education.util.aliyun.AliyunOssUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.DateUtil;


/**
 * 广告信息 
 *
 * @author wujing
 */
@Component
public class AdvBiz extends BaseBiz{

	@Autowired
	private IBossAdv bossAdv;

	public Page<AdvVO> listForPage(AdvQO qo) {
        return bossAdv.listForPage(qo);
	}

	public int save(AdvQO qo,MultipartFile advFile) {
		if (advFile != null && !advFile.isEmpty()) {
			qo.setAdvImg(AliyunOssUtil.uploadPic(PlatformEnum.COURSE, advFile));
		}
		qo.setBeginTime(DateUtil.parseDate(qo.getBeginTimeString(), "yyyy-MM-dd HH:mm:ss"));
		qo.setEndTime(DateUtil.parseDate(qo.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
		return bossAdv.save(qo);
	}

	public int deleteById(Long id) {
		return bossAdv.deleteById(id);
	}

	public AdvVO getById(Long id) {
		return bossAdv.getById(id);
	}

	public int updateById(AdvQO qo, MultipartFile advFile) {
		if (advFile != null && !advFile.isEmpty()) {
			qo.setAdvImg(AliyunOssUtil.uploadPic(PlatformEnum.COURSE, advFile));
		}
		qo.setBeginTime(DateUtil.parseDate(qo.getBeginTimeString(), "yyyy-MM-dd HH:mm:ss"));
		qo.setEndTime(DateUtil.parseDate(qo.getEndTimeString(), "yyyy-MM-dd HH:mm:ss"));
		return bossAdv.updateById(qo);
	}

	public int updateStautsId(AdvQO qo) {
		return bossAdv.updateById(qo);
	}
	
}
