package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiMsgTemplateBiz;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.MsgTemplatePageRESQ;
import com.roncoo.education.system.service.pc.resq.MsgTemplateViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息模板
 */
@RestController
@RequestMapping(value = "/system/pc/msg/template")
public class PcApiMsgTemplateController extends BaseController {

    @Autowired
    private PcApiMsgTemplateBiz biz;

    /**
     * 消息模板分页列表接口
     */
    @ApiOperation(value = "消息模板分页列表接口", notes = "消息模板分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<MsgTemplatePageRESQ>> list(@RequestBody MsgTemplatePageREQ msgTemplatePageREQ) {
        return biz.list(msgTemplatePageREQ);
    }

    /**
     * 消息模板保存接口
     */
    @ApiOperation(value = "消息模板保存接口", notes = "消息模板保存接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody MsgTemplateSaveREQ msgTemplateSaveREQ) {
        return biz.save(msgTemplateSaveREQ);
    }

    /**
     * 消息模板删除接口
     */
    @ApiOperation(value = "消息模板删除接口", notes = "消息模板删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody MsgTemplateDeleteREQ msgTemplateDeleteREQ) {
        return biz.delete(msgTemplateDeleteREQ);
    }

    /**
     * 消息模板更新接口
     */
    @ApiOperation(value = "消息模板更新接口", notes = "消息模板更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody MsgTemplateUpdeteREQ msgTemplateUpdeteREQ) {
        return biz.update(msgTemplateUpdeteREQ);
    }

    /**
     * 消息模板更新接口
     */
    @ApiOperation(value = "消息模板查看接口", notes = "消息模板查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<MsgTemplateViewRESQ> view(@RequestBody MsgTemplateViewREQ msgTemplateViewREQ) {
        return biz.view(msgTemplateViewREQ);
    }
}
