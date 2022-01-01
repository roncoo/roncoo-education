package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignOrderInfoBiz;
import com.roncoo.education.course.feign.interfaces.IFeignOrderInfo;
import com.roncoo.education.course.feign.interfaces.qo.OrderEchartsQO;
import com.roncoo.education.course.feign.interfaces.qo.OrderInfoQO;
import com.roncoo.education.course.feign.interfaces.vo.CountIncomeVO;
import com.roncoo.education.course.feign.interfaces.vo.OrderEchartsVO;
import com.roncoo.education.course.feign.interfaces.vo.OrderInfoVO;
import com.roncoo.education.course.feign.interfaces.vo.OrderReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单信息表
 *
 * @author wujing
 */
@RestController
public class FeignOrderInfoController extends BaseController implements IFeignOrderInfo {

    @Autowired
    private FeignOrderInfoBiz biz;

    @Override
    public Page<OrderInfoVO> listForPage(@RequestBody OrderInfoQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody OrderInfoQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody OrderInfoQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public OrderInfoVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public List<OrderReportVO> listForReport(@RequestBody OrderInfoQO orderInfoQO) {
        return biz.listForReport(orderInfoQO);
    }

    @Override
    public List<OrderEchartsVO> sumByCountOrders(@RequestBody OrderEchartsQO orderEchartsQO) {
        return biz.sumByCountOrders(orderEchartsQO);
    }

    @Override
    public List<OrderEchartsVO> sumByPayTime(@RequestBody OrderEchartsQO orderEchartsQO) {
        return biz.sumByPayTime(orderEchartsQO);
    }

    @Override
    public int handleScheduledTasks() {
        return biz.handleScheduledTasks();
    }

    @Override
    public CountIncomeVO countIncome(@RequestBody OrderInfoQO qo) {
        return biz.countIncome(qo);
    }

}
