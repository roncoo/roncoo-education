package com.roncoo.education.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.user.common.bean.qo.LecturerProfitQO;
import com.roncoo.education.user.common.bean.vo.LecturerProfitVO;
import com.roncoo.education.user.common.interfaces.BossLecturerProfit;
import com.roncoo.education.user.service.controller.biz.BossLecturerProfitBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 讲师提现日志表 
 *
 * @author wujing
 */
@RestController
public class BossLecturerProfitController extends BaseController implements BossLecturerProfit{

	@Autowired
	private BossLecturerProfitBiz biz;
	
	@Override
	public Page<LecturerProfitVO> listForPage(@RequestBody LecturerProfitQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody LecturerProfitQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody LecturerProfitQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public LecturerProfitVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}
}
