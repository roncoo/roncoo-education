package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.DicQO;
import com.roncoo.education.course.common.bean.vo.DicVO;
import com.roncoo.education.util.base.Page;

/**
 * 数据字典 
 *
 * @author wujing
 */
public interface BossDic {
	
	@RequestMapping(value = "/boss/course/dic/listForPage", method = RequestMethod.POST)
	Page<DicVO> listForPage(@RequestBody DicQO qo);
	
	@RequestMapping(value = "/boss/course/dic/save", method = RequestMethod.POST)
	int save(@RequestBody DicQO qo);
	
	@RequestMapping(value = "/boss/course/dic/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/dic/update", method = RequestMethod.PUT)
	int updateById(@RequestBody DicQO qo);
	
	@RequestMapping(value = "/boss/course/dic/get/{id}", method = RequestMethod.GET)
	DicVO getById(@PathVariable(value = "id") Long id);
	
}
