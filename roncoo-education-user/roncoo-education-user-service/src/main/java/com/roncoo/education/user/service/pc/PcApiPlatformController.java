package com.roncoo.education.user.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.pc.biz.PcApiPlatformBiz;
import com.roncoo.education.user.service.pc.req.*;
import com.roncoo.education.user.service.pc.resq.PlatformPageRESQ;
import com.roncoo.education.user.service.pc.resq.PlatformViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/pc/platform")
public class PcApiPlatformController extends BaseController {

    @Autowired
    private PcApiPlatformBiz biz;

    /**
     * 平台信息分页列表接口
     *
     * @param lecturerViewPageREQ
     */
    @ApiOperation(value = "平台信息分页列表接口", notes = "平台信息分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<PlatformPageRESQ>> listForPage(@RequestBody PlatformPageREQ platformPageREQ) {
        return biz.listForPage(platformPageREQ);
    }

    /**
     * 保存平台信息接口
     */
    @ApiOperation(value = "保存平台信息接口", notes = "保存平台信息接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody PlatformSaveREQ platformSaveREQ) {
        return biz.save(platformSaveREQ);
    }

    /**
     * 删除平台信息接口
     */
    @ApiOperation(value = "删除平台信息接口", notes = "删除平台信息接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody PlatformDeleteREQ platformDeleteREQ) {
        return biz.delete(platformDeleteREQ);
    }

    /**
     * 更新平台信息接口
     */
    @ApiOperation(value = "更新平台信息接口", notes = "更新平台信息接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody PlatformUpdateREQ platformUpdateREQ) {
        return biz.update(platformUpdateREQ);
    }

    /**
     * 平台信息信息查看接口
     *
     * @param lecturerViewREQ
     */
    @ApiOperation(value = "平台信息查看接口", notes = "根据平台信息用户编号查看平台信息信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<PlatformViewRESQ> view(@RequestBody PlatformViewREQ platformViewREQ) {
        return biz.view(platformViewREQ);
    }

}
