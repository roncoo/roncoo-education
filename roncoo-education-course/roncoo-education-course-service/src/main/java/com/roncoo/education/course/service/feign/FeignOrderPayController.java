package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignOrderPay;
import com.roncoo.education.course.feign.qo.OrderPayQO;
import com.roncoo.education.course.feign.vo.OrderPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignOrderPayBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@RestController
public class FeignOrderPayController extends BaseController implements IFeignOrderPay {

    @Autowired
    private FeignOrderPayBiz biz;

    @Override
    public Page<OrderPayVO> listForPage(@RequestBody OrderPayQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody OrderPayQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody OrderPayQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public OrderPayVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
