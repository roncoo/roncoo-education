package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.LecturerAuditQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerAuditVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignLecturerAudit {

    @RequestMapping(value = "/feign/user/lecturerAudit/listForPage", method = RequestMethod.POST)
    Page<LecturerAuditVO> listForPage(@RequestBody LecturerAuditQO qo);

    @RequestMapping(value = "/feign/user/lecturerAudit/save", method = RequestMethod.POST)
    int save(@RequestBody LecturerAuditQO qo);

    @RequestMapping(value = "/feign/user/lecturerAudit/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/lecturerAudit/update", method = RequestMethod.PUT)
    int updateById(@RequestBody LecturerAuditQO qo);

    @RequestMapping(value = "/feign/user/lecturerAudit/get/{id}", method = RequestMethod.GET)
    LecturerAuditVO getById(@PathVariable(value = "id") Long id);

    /**
     * 审核
     *
     * @param qo
     * @return
     */
    @RequestMapping(value = "/feign/user/lecturerAudit/audit", method = RequestMethod.POST)
    int audit(@RequestBody LecturerAuditQO qo);

    /**
     * 讲师添加时候的手机号码校验
     *
     * @param qo
     * @return
     */
    @RequestMapping(value = "/feign/user/lecturerAudit/checkUserAndLecturer", method = RequestMethod.POST)
    LecturerAuditVO checkUserAndLecturer(LecturerAuditQO qo);

}
