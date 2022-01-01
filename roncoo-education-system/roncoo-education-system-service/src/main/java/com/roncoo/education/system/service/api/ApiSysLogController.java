package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.system.service.api.biz.ApiSysLogBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
