package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.OrderPayPageQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderPaySaveQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderPayEditQO;
import com.roncoo.education.user.feign.interfaces.vo.OrderPayPageVO;
import com.roncoo.education.user.feign.interfaces.vo.OrderPayViewVO;

/**
 * 订单支付信息表 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "user-service", path = "/user/order/pay")
public interface IFeignOrderPay {

    /**
     * 分页列出-订单支付信息表
     *
     * @param qo 订单支付信息表
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<OrderPayPageVO> page(@RequestBody OrderPayPageQO qo);

    /**
     * 保存-订单支付信息表
     *
     * @param qo 订单支付信息表
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody OrderPaySaveQO qo);

    /**
     * 根据ID删除-订单支付信息表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改订单支付信息表
     *
     * @param qo 订单支付信息表
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody OrderPayEditQO qo);

    /**
     * 根据ID获取订单支付信息表
     *
     * @param id 主键ID
     * @return 订单支付信息表
     */
    @GetMapping(value = "/get/{id}")
    OrderPayViewVO getById(@PathVariable(value = "id") Long id);
}
