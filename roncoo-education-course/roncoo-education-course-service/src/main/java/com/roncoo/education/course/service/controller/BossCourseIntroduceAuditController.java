package com.roncoo.education.course.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.common.bean.qo.CourseIntroduceAuditQO;
import com.roncoo.education.course.common.bean.vo.CourseIntroduceAuditVO;
import com.roncoo.education.course.common.interfaces.BossCourseIntroduceAudit;
import com.roncoo.education.course.service.controller.biz.BossCourseIntroduceAuditBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 课程介绍信息 
 *
 * @author wujing
 */
@RestController
public class BossCourseIntroduceAuditController extends BaseController implements BossCourseIntroduceAudit{

	@Autowired
	private BossCourseIntroduceAuditBiz biz;
	
	@Override
	public Page<CourseIntroduceAuditVO> listForPage(@RequestBody CourseIntroduceAuditQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody CourseIntroduceAuditQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody CourseIntroduceAuditQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public CourseIntroduceAuditVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}
	
}
