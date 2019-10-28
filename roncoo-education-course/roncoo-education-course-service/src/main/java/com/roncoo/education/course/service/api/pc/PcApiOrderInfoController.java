package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.api.pc.biz.PcApiOrderInfoBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.req.OrderInfoPageREQ;
import com.roncoo.education.course.common.req.OrderInfoStatisticalREQ;
import com.roncoo.education.course.common.req.OrderInfoUpdateREQ;
import com.roncoo.education.course.common.req.OrderInfoViewREQ;
import com.roncoo.education.course.common.resq.CountIncomeRESQ;
import com.roncoo.education.course.common.resq.OrderInfoPageRESQ;
import com.roncoo.education.course.common.resq.OrderInfoViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 订单信息表
 */
@RestController
@RequestMapping(value = "/course/pc/order/info")
public class PcApiOrderInfoController extends BaseController {

    @Autowired
    private PcApiOrderInfoBiz biz;

    /**
     * 订单分页列表接口
     */
    @ApiOperation(value = "订单分页列表接口", notes = "订单分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<OrderInfoPageRESQ>> listForPage(@RequestBody OrderInfoPageREQ orderInfoPageREQ) {
        return biz.listForPage(orderInfoPageREQ);
    }

    /**
     * 订单查看接口
     */
    @ApiOperation(value = "订单查看接口", notes = "订单查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<OrderInfoViewRESQ> view(@RequestBody OrderInfoViewREQ orderInfoViewREQ) {
        return biz.view(orderInfoViewREQ);
    }

    /**
     * 订单后台备注更新接口
     */
    @ApiOperation(value = "订单后台备注更新接口", notes = "订单后台备注更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody OrderInfoUpdateREQ orderInfoUpdateREQ) {
        return biz.update(orderInfoUpdateREQ);
    }

    /**
     * 统计订单收入情况接口
     */
    @ApiOperation(value = "统计订单收入情况接口", notes = "统计订单收入情况接口")
    @RequestMapping(value = "/statistical", method = RequestMethod.POST)
    public Result<CountIncomeRESQ> statistical(@RequestBody OrderInfoStatisticalREQ orderInfoStatisticalREQ) {
        return biz.statistical(orderInfoStatisticalREQ);
    }

}
