package com.roncoo.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.api.biz.ApiSysLogBiz;
import com.roncoo.education.util.base.BaseController;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@RestController
public class ApiSysLogController extends BaseController {

    @Autowired
    private ApiSysLogBiz biz;

}
