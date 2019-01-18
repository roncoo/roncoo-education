package com.roncoo.education.course.common.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.ZoneQO;
import com.roncoo.education.course.common.bean.vo.ZoneVO;
import com.roncoo.education.util.base.Page;

/**
 * 专区
 *
 * @author wujing
 */
public interface BossZone {

	@RequestMapping(value = "/boss/course/zone/listForPage", method = RequestMethod.POST)
	Page<ZoneVO> listForPage(@RequestBody ZoneQO qo);

	@RequestMapping(value = "/boss/course/zone/save", method = RequestMethod.POST)
	int save(@RequestBody ZoneQO qo);

	@RequestMapping(value = "/boss/course/zone/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/zone/update", method = RequestMethod.PUT)
	int updateById(@RequestBody ZoneQO qo);

	@RequestMapping(value = "/boss/course/zone/get/{id}", method = RequestMethod.GET)
	ZoneVO getById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/zone/listAllZone", method = RequestMethod.GET)
	List<ZoneVO> listAllZone();
}
