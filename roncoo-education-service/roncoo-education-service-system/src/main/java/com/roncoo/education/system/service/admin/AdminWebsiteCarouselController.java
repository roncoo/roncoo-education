package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.log.SysLog;
import com.roncoo.education.common.log.SysLogCache;
import com.roncoo.education.system.service.admin.biz.AdminWebsiteCarouselBiz;
import com.roncoo.education.system.service.admin.req.AdminWebsiteCarouselEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteCarouselPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteCarouselSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteCarouselPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteCarouselViewResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN-广告信息
 *
 * @author wujing
 */
@Tag(name = "admin-广告信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/website/carousel")
public class AdminWebsiteCarouselController {

    @NotNull
    private final AdminWebsiteCarouselBiz biz;

    @Operation(summary = "广告信息分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminWebsiteCarouselPageResp>> page(@RequestBody AdminWebsiteCarouselPageReq req) {
        return biz.page(req);
    }

    @Operation(summary = "广告信息添加")
    @SysLog(value = "广告信息添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminWebsiteCarouselSaveReq req) {
        return biz.save(req);
    }

    @Operation(summary = "广告信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLogCache
    @GetMapping(value = "/view")
    public Result<AdminWebsiteCarouselViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @Operation(summary = "广告信息修改")
    @SysLog(value = "广告信息修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminWebsiteCarouselEditReq req) {
        return biz.edit(req);
    }

    @Operation(summary = "广告信息删除")
    @Parameter(name = "id", description = "主键ID", required = true)
    @SysLog(value = "广告信息删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @Operation(summary = "排序")
    @SysLog(value = "排序")
    @PutMapping(value = "/sort")
    public Result<Integer> sort(@RequestBody List<SortReq> req) {
        return Result.success(biz.sort(req, "WebsiteCarousel"));
    }
}
