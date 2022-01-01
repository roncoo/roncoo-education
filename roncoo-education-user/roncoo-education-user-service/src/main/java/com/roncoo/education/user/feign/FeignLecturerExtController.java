package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.biz.FeignLecturerExtBiz;
import com.roncoo.education.user.feign.interfaces.IFeignLecturerExt;
import com.roncoo.education.user.feign.interfaces.qo.LecturerExtQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerExtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 讲师账户信息表
 *
 * @author wujing
 */
@RestController
public class FeignLecturerExtController extends BaseController implements IFeignLecturerExt {

    @Autowired
    private FeignLecturerExtBiz biz;

    @Override
    public Page<LecturerExtVO> listForPage(@RequestBody LecturerExtQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody LecturerExtQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody LecturerExtQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public LecturerExtVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public LecturerExtVO getByLecturerUserNo(@PathVariable(value = "lecturerUserNo") Long lecturerUserNo) {
        return biz.getByLecturerUserNo(lecturerUserNo);
    }

    @Override
    public void updateTotalIncomeByLecturerUserNo(@RequestBody LecturerExtQO qo) {
        biz.updateTotalIncomeByLecturerUserNo(qo);
    }

}
