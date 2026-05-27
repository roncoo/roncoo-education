package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteLinkBiz;
import com.roncoo.education.system.service.api.resp.ApiWebsiteLinkResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 友情链接
 *
 * @author wuyun
 */
@Tag(name = "api-友情链接")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/system/api/website/link")
public class ApiWebsiteLinkController {

    @NotNull
    private final ApiWebsiteLinkBiz biz;

    /**
     * 友情链接接口
     *
     * @return 返回友情链接列表
     * @author wuyun
     */
    @Operation(summary = "友情链接接口", description = "返回友情链接列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<ApiWebsiteLinkResp>> list() {
        return biz.list();
    }

}
