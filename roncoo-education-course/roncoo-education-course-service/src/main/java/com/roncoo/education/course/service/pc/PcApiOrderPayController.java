package com.roncoo.education.course.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.pc.biz.PcApiOrderPayBiz;
import com.roncoo.education.course.service.pc.req.OrderPayPageREQ;
import com.roncoo.education.course.service.pc.resq.OrderPayPageRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付记录
 */
@RestController
@RequestMapping(value = "/course/pc/order/pay")
public class PcApiOrderPayController extends BaseController {

    @Autowired
    private PcApiOrderPayBiz biz;

    /**
     * 分页列出支付记录
     *
     * @return
     */
    @ApiOperation(value = "支付记录分页列表接口", notes = "支付记录分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<OrderPayPageRESQ>> list(@RequestBody OrderPayPageREQ orderPayPageREQ) {
        return biz.list(orderPayPageREQ);
    }

}
