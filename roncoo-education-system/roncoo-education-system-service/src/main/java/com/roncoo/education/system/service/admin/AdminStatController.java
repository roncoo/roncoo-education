package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminStatBiz;
import com.roncoo.education.system.service.admin.resp.StatVodResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@RestController
@RequestMapping("/system/admin/stat")
@Api(value = "system-点播直播统计", tags = {"system-点播直播统计"})
public class AdminStatController {

    @Autowired
    private AdminStatBiz biz;

    @ApiOperation(value = "点播", notes = "点播空间和流量的统计")
    @PostMapping(value = "/vod")
    public Result<StatVodResp> vod() {
        return biz.vod();
    }

}
