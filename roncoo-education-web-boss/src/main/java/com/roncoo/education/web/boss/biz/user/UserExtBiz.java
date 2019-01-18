package com.roncoo.education.web.boss.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.user.common.bean.qo.UserExtQO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@Component
public class UserExtBiz extends BaseBiz {

	@Autowired
	private IBossSys bossSys;
	@Autowired
	private IBossUserExt bossUserExt;

	public Page<UserExtVO> listForPage(UserExtQO qo) {
		return bossUserExt.listForPage(qo);
	}

	public int save(UserExtQO qo) {
		return bossUserExt.save(qo);
	}

	public int deleteById(Long id) {
		return bossUserExt.deleteById(id);
	}

	public UserExtVO getById(Long id) {
		return bossUserExt.getById(id);
	}

	public int updateById(UserExtQO qo, MultipartFile headImgFile) {
		if (headImgFile != null && !headImgFile.isEmpty()) {
			qo.setHeadImgUrl(AliyunUtil.uploadPic(PlatformEnum.USER, headImgFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return bossUserExt.updateById(qo);
	}

	public UserExtVO getByUserNo(Long userNo) {
		return bossUserExt.getByUserNo(userNo);
	}

	public int updateStatusId(UserExtQO qo) {
		return bossUserExt.updateById(qo);
	}

}
