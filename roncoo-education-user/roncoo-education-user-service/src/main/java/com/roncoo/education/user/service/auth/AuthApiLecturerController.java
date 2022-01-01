package com.roncoo.education.user.service.auth;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.bo.LecturerViewBO;
import com.roncoo.education.user.service.api.dto.LecturerViewDTO;
import com.roncoo.education.user.service.auth.biz.AuthApiLecturerBiz;
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
@RequestMapping(value = "/user/auth/lecturer")
public class AuthApiLecturerController extends BaseController {

    @Autowired
    private AuthApiLecturerBiz biz;

    /**
     * 讲师信息查看接口
     *
     * @param lecturerUserNo
     * @author wuyun
     */
    @ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<LecturerViewDTO> view(@RequestBody LecturerViewBO lecturerViewBO) {
        return biz.view(lecturerViewBO);
    }

}
