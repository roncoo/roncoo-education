package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignOrderInfoBiz;
import com.roncoo.education.user.feign.interfaces.IFeignOrderInfo;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoEditQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoPageQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.OrderInfoPageVO;
import com.roncoo.education.user.feign.interfaces.vo.OrderInfoViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 订单信息表
 *
 * @author wujing
 * @date 2022-09-06
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/order/info")
public class FeignOrderInfoController extends BaseController implements IFeignOrderInfo {

    @NotNull
    private final FeignOrderInfoBiz biz;

    @Override
    public Page<OrderInfoPageVO> page(@RequestBody OrderInfoPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody OrderInfoSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody OrderInfoEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public OrderInfoViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
