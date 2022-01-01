package com.roncoo.education.course.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.api.biz.ApiAdvBiz;
import com.roncoo.education.course.service.api.bo.AdvBO;
import com.roncoo.education.course.service.api.dto.AdvListDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 广告信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/course/api/adv")
public class ApiAdvController extends BaseController {

    @Autowired
    private ApiAdvBiz biz;

    @ApiOperation(value = "广告列表接口", notes = "首页轮播广告列出")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<AdvListDTO> list(@RequestBody AdvBO advBO) {
        return biz.list(advBO);
    }

}
