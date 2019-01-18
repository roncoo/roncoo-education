package com.roncoo.education.course.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.common.bean.qo.DicListQO;
import com.roncoo.education.course.common.bean.vo.DicListVO;
import com.roncoo.education.course.common.interfaces.BossDicList;
import com.roncoo.education.course.service.controller.biz.BossDicListBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 数据字典明细表 
 *
 * @author wujing
 */
@RestController
public class BossDicListController extends BaseController implements BossDicList{

	@Autowired
	private BossDicListBiz biz;
	
	@Override
	public Page<DicListVO> listForPage(@RequestBody DicListQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody DicListQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody DicListQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public DicListVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}
	
}
