package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiMsgBiz;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.MsgPageRESQ;
import com.roncoo.education.system.service.pc.resq.MsgViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站内信消息
 */
@RestController
@RequestMapping(value = "/system/pc/msg")
public class PcApiMsgController extends BaseController {

    @Autowired
    private PcApiMsgBiz biz;

    /**
     * 站内信消息分页列表接口
     */
    @ApiOperation(value = "站内信消息分页列表接口", notes = "站内信消息分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<MsgPageRESQ>> list(@RequestBody MsgPageREQ msgPageREQ) {
        return biz.list(msgPageREQ);
    }

    /**
     * 站内信消息保存接口
     */
    @ApiOperation(value = "站内信消息保存接口", notes = "站内信消息保存接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody MsgSaveREQ msgSaveREQ) {
        return biz.save(msgSaveREQ);
    }

    /**
     * 站内信消息删除接口
     */
    @ApiOperation(value = "站内信消息删除接口", notes = "站内信消息删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody MsgDeleteREQ msgDeleteREQ) {
        return biz.delete(msgDeleteREQ);
    }

    /**
     * 站内信消息更新接口
     */
    @ApiOperation(value = "站内信消息更新接口", notes = "站内信消息更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody MsgUpdateREQ msgUpdateREQ) {
        return biz.update(msgUpdateREQ);
    }

    /**
     * 站内信消息查看接口
     */
    @ApiOperation(value = "站内信消息查看接口", notes = "站内信消息查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<MsgViewRESQ> view(@RequestBody MsgViewREQ msgViewREQ) {
        return biz.view(msgViewREQ);
    }

    /**
     * 站内信消息手动发送接口
     */
    @ApiOperation(value = "站内信消息手动发送接口", notes = "站内信消息手动发送接口")
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public Result<Integer> push(@RequestBody MsgPushREQ msgPushREQ) {
        return biz.push(msgPushREQ);
    }

}
