package com.roncoo.education.user.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.pc.biz.PcApiLecturerProfitBiz;
import com.roncoo.education.user.service.pc.req.LecturerProfitBatchREQ;
import com.roncoo.education.user.service.pc.req.LecturerProfitPageREQ;
import com.roncoo.education.user.service.pc.req.LecturerProfitUpdateREQ;
import com.roncoo.education.user.service.pc.resq.LecturerProfitPageRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 讲师提现日志
 */
@RestController
@RequestMapping(value = "/user/pc/lecturer/profit")
public class PcApiLecturerProfitController extends BaseController {

    @Autowired
    private PcApiLecturerProfitBiz biz;

    /**
     * 讲师提现分页列表接口
     *
     * @param lecturerProfitPageREQ
     */
    @ApiOperation(value = "讲师提现分页列表接口", notes = "讲师提现分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<LecturerProfitPageRESQ>> listForPage(@RequestBody LecturerProfitPageREQ lecturerProfitPageREQ) {
        return biz.listForPage(lecturerProfitPageREQ);
    }

    /**
     * 讲师提现更新提现状态接口
     *
     * @param LecturerProfitUpdateREQ
     */
    @ApiOperation(value = "讲师提现更新提现状态接口", notes = "讲师提现更新提现状态接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody LecturerProfitUpdateREQ LecturerProfitUpdateREQ) {
        return biz.update(LecturerProfitUpdateREQ);
    }

    /**
     * 讲师提现批量更新提现状态接口
     *
     * @param lecturerProfitBatchREQ
     */
    @ApiOperation(value = "讲师提现批量更新提现状态接口", notes = "讲师提现批量更新提现状态接口")
    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    public Result<Integer> batch(@RequestBody LecturerProfitBatchREQ lecturerProfitBatchREQ) {
        return biz.batch(lecturerProfitBatchREQ);
    }

}
