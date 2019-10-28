package com.roncoo.education.user.service.feign;

import java.util.List;

import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.feign.qo.LecturerQO;
import com.roncoo.education.user.feign.vo.LecturerVO;
import com.roncoo.education.user.service.feign.biz.FeignLecturerBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 讲师信息
 *
 * @author wujing
 */
@RestController
public class FeignLecturerController extends BaseController implements IFeignLecturer {

	@Autowired
	private FeignLecturerBiz biz;

	@Override
	public Page<LecturerVO> listForPage(@RequestBody LecturerQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody LecturerQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody LecturerQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public LecturerVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	/**
	 * 列出所有讲师信息
	 */
	@Override
	public List<LecturerVO> listAllForLecturer() {
		return biz.listAllForLecturer();
	}

	/***
	 * 根据讲师用户编号查找讲师信息
	 */
	@Override
	public LecturerVO getByLecturerUserNo(@PathVariable(value = "lecturerUserNo") Long lecturerUserNo) {
		return biz.getByLecturerUserNo(lecturerUserNo);
	}
}
