package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.DicListQO;
import com.roncoo.education.course.common.bean.vo.DicListVO;
import com.roncoo.education.util.base.Page;

/**
 * 数据字典明细表 
 *
 * @author wujing
 */
public interface BossDicList {
	
	@RequestMapping(value = "/boss/course/dicList/listForPage", method = RequestMethod.POST)
	Page<DicListVO> listForPage(@RequestBody DicListQO qo);
	
	@RequestMapping(value = "/boss/course/dicList/save", method = RequestMethod.POST)
	int save(@RequestBody DicListQO qo);
	
	@RequestMapping(value = "/boss/course/dicList/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/dicList/update", method = RequestMethod.PUT)
	int updateById(@RequestBody DicListQO qo);
	
	@RequestMapping(value = "/boss/course/dicList/get/{id}", method = RequestMethod.GET)
	DicListVO getById(@PathVariable(value = "id") Long id);
	
}
