package com.roncoo.education.user.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.bo.LecturerViewBO;
import com.roncoo.education.user.common.bean.dto.LecturerViewDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 讲师信息
 *
 * @author wujing
 */
public interface ApiLecturer {

	/**
	 * 讲师信息查看接口
	 * 
	 * @param lecturerUserNo
	 * @author wuyun
	 */
	@ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
	@RequestMapping(value = "/user/api/lecturer/view", method = RequestMethod.POST)
	Result<LecturerViewDTO> view(@RequestBody LecturerViewBO lecturerViewBO);

}
