package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.biz.FeignLecturerAuditBiz;
import com.roncoo.education.user.feign.interfaces.IFeignLecturerAudit;
import com.roncoo.education.user.feign.interfaces.qo.LecturerAuditQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
public class FeignLecturerAuditController extends BaseController implements IFeignLecturerAudit {

    @Autowired
    private FeignLecturerAuditBiz biz;

    @Override
    public Page<LecturerAuditVO> listForPage(@RequestBody LecturerAuditQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody LecturerAuditQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody LecturerAuditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public LecturerAuditVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public int audit(@RequestBody LecturerAuditQO qo) {
        return biz.audit(qo);
    }

    @Override
    public LecturerAuditVO checkUserAndLecturer(@RequestBody LecturerAuditQO qo) {
        return biz.checkUserAndLecturer(qo);
    }

}
