package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.video.resp.InfoResp;
import com.roncoo.education.system.service.admin.biz.AdminStatBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@Api(value = "admin-点播直播统计")
@RestController
@RequestMapping("/system/admin/stat")
public class AdminStatController {

    @Autowired
    private AdminStatBiz biz;

    @ApiOperation(value = "点播", notes = "点播空间和流量的统计")
    @GetMapping(value = "/vod")
    public Result<InfoResp> vod() {
        return biz.vod();
    }

}
