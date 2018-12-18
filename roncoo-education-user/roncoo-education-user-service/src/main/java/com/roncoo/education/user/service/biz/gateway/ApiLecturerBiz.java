package com.roncoo.education.user.service.biz.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.common.bean.dto.LecturerViewDTO;
import com.roncoo.education.user.service.dao.LecturerDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 讲师信息
 *
 * @author wuyun
 */
@Component
public class ApiLecturerBiz {

	@Autowired
	private LecturerDao lecturerDao;

	/**
	 * 讲师信息查看接口
	 * 
	 * @param lecturerUserNo
	 * @author wuyun
	 */
	public Result<LecturerViewDTO> view(Long lecturerUserNo) {
		if (null == lecturerUserNo) {
			return Result.error("讲师编号不能为空");
		}
		Lecturer lecturer = lecturerDao.getByLecturerUserNo(lecturerUserNo);
		if (ObjectUtil.isNull(lecturer)) {
			return Result.error("找不到该讲师");
		}
		return Result.success(BeanUtil.copyProperties(lecturer, LecturerViewDTO.class));
	}

}
