package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.auth.biz.AuthApiLecturerAuditBiz;
import com.roncoo.education.user.service.auth.bo.AuthLecturerAuditBO;
import com.roncoo.education.user.service.auth.bo.AuthLecturerAuditSaveBO;
import com.roncoo.education.user.service.auth.bo.AuthLecturerAuditViewBO;
import com.roncoo.education.user.service.auth.dto.AuthLecturerAuditViewDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/auth/lecturer/audit")
public class AuthApiLecturerAuditController extends BaseController {

    @Autowired
    private AuthApiLecturerAuditBiz biz;

    /**
     * 讲师信息修改接口
     */
    @ApiOperation(value = "讲师修改接口", notes = "修改讲师信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody AuthLecturerAuditBO authLecturerAuditBO) {
        return biz.update(authLecturerAuditBO);
    }

    /**
     * 讲师信息查看接口
     */
    @ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AuthLecturerAuditViewDTO> view(@RequestBody AuthLecturerAuditViewBO authLecturerAuditViewBO) {
        return biz.view(authLecturerAuditViewBO);
    }

    /**
     * 讲师申请接口
     *
     * @param authLecturerAuditSaveBO
     * @author wuyun
     */
    @ApiOperation(value = "讲师申请接口", notes = "保存讲师信息接口,需要审核")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody AuthLecturerAuditSaveBO authLecturerAuditSaveBO) {
        return biz.save(authLecturerAuditSaveBO);
    }

}
