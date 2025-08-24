package com.roncoo.education.user.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.admin.biz.AdminStatBiz;
import com.roncoo.education.user.service.admin.resp.AdminStatDataResp;
import com.roncoo.education.user.service.admin.resp.AdminStatLoginResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@Api(tags = "admin-用户登录统计")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin/stat")
public class AdminStatController {

    @NotNull
    private final AdminStatBiz biz;

    @ApiOperation(value = "登录统计", notes = "登录统计")
    @GetMapping(value = "/login")
    public Result<AdminStatLoginResp> statLogin(@RequestParam Integer dates) {
        return biz.statLogin(dates);
    }

    @ApiOperation(value = "数据统计", notes = "数据统计")
    @GetMapping(value = "/data")
    public Result<AdminStatDataResp> statData() {
        return biz.statData();
    }

}
