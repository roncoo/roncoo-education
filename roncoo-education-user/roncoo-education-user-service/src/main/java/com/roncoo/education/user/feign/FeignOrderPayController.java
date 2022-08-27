package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignOrderPayBiz;
import com.roncoo.education.user.feign.interfaces.IFeignOrderPay;
import com.roncoo.education.user.feign.interfaces.qo.OrderPayEditQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderPayPageQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderPaySaveQO;
import com.roncoo.education.user.feign.interfaces.vo.OrderPayPageVO;
import com.roncoo.education.user.feign.interfaces.vo.OrderPayViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 订单支付信息表
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/order/pay")
public class FeignOrderPayController extends BaseController implements IFeignOrderPay {

    @NotNull
    private final FeignOrderPayBiz biz;

    @Override
    public Page<OrderPayPageVO> page(@RequestBody OrderPayPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody OrderPaySaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody OrderPayEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public OrderPayViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
