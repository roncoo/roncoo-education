package com.roncoo.education.system.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.interfaces.gateway.ApiSys;
import com.roncoo.education.system.service.biz.gateway.ApiSysBiz;
import com.roncoo.education.util.base.BaseController;

/**
 * 系统配置表 
 *
 * @author YZJ
 */
@RestController
public class ApiSysController extends BaseController implements ApiSys{

    @Autowired
    private ApiSysBiz biz;
    
}
