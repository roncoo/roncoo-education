package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.AdvQO;
import com.roncoo.education.course.common.bean.vo.AdvVO;
import com.roncoo.education.util.base.Page;

/**
 * 广告信息 
 *
 * @author wujing
 */
public interface BossAdv {
	
	@RequestMapping(value = "/boss/course/adv/listForPage", method = RequestMethod.POST)
	Page<AdvVO> listForPage(@RequestBody AdvQO qo);
	
	@RequestMapping(value = "/boss/course/adv/save", method = RequestMethod.POST)
	int save(@RequestBody AdvQO qo);
	
	@RequestMapping(value = "/boss/course/adv/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/adv/update", method = RequestMethod.PUT)
	int updateById(@RequestBody AdvQO qo);
	
	@RequestMapping(value = "/boss/course/adv/get/{id}", method = RequestMethod.GET)
	AdvVO getById(@PathVariable(value = "id") Long id);
	
}
