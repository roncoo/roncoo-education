package com.roncoo.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.common.bean.qo.DicQO;
import com.roncoo.education.course.common.bean.vo.DicVO;
import com.roncoo.education.course.feign.IBossDic;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 数据字典 
 *
 * @author wujing
 */
@Component
public class DicBiz extends BaseBiz{

	@Autowired
	private IBossDic bossDic;

	public Page<DicVO> listForPage(DicQO qo) {
        return bossDic.listForPage(qo);
	}

	public int save(DicQO qo) {
		return bossDic.save(qo);
	}

	public int deleteById(Long id) {
		return bossDic.deleteById(id);
	}

	public DicVO getById(Long id) {
		return bossDic.getById(id);
	}

	public int updateById(DicQO qo) {
		return bossDic.updateById(qo);
	}
	
}
