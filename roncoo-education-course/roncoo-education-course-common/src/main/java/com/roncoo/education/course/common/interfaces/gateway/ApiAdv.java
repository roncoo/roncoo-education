package com.roncoo.education.course.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.bo.AdvBO;
import com.roncoo.education.course.common.bean.dto.AdvListDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 广告信息 
 *
 * @author wujing
 */
public interface ApiAdv {
	
	/**
	 * 列出接口
	 */
	@ApiOperation(value = "广告列表接口", notes = "首页轮播广告列出")
	@RequestMapping(value = "/course/api/adv/list", method = RequestMethod.POST)
	Result<AdvListDTO> list(@RequestBody AdvBO bo);
    
}
