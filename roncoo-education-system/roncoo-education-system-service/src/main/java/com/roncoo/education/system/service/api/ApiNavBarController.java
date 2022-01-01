package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiNavBarBiz;
import com.roncoo.education.system.service.api.dto.NavBarListDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 头部导航
 *
 * @author wuyun
 */
@RestController
public class ApiNavBarController extends BaseController {

    @Autowired
    private ApiNavBarBiz biz;

    /**
     * 获取头部导航信息接口
     *
     * @return 站点信息
     * @author wuyun
     */
    @ApiOperation(value = "获取头部导航信息接口", notes = "获取头部导航信息")
    @RequestMapping(value = "/system/api/nav/bar/list", method = RequestMethod.POST)
    public Result<NavBarListDTO> list() {
        return biz.list();
    }

}
