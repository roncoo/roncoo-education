package com.roncoo.education.course.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.common.bean.qo.DicQO;
import com.roncoo.education.course.common.bean.vo.DicVO;
import com.roncoo.education.course.common.interfaces.BossDic;
import com.roncoo.education.course.service.controller.biz.BossDicBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 数据字典 
 *
 * @author wujing
 */
@RestController
public class BossDicController extends BaseController implements BossDic{

	@Autowired
	private BossDicBiz biz;
	
	@Override
	public Page<DicVO> listForPage(@RequestBody DicQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody DicQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody DicQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public DicVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}
	
}
