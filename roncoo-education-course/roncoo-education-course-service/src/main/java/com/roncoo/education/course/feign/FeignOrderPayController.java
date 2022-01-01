package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignOrderPayBiz;
import com.roncoo.education.course.feign.interfaces.IFeignOrderPay;
import com.roncoo.education.course.feign.interfaces.qo.OrderPayQO;
import com.roncoo.education.course.feign.interfaces.vo.OrderPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
