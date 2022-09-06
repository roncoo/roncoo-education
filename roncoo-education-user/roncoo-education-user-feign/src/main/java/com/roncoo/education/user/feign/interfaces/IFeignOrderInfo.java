package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoPageQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoSaveQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoEditQO;
import com.roncoo.education.user.feign.interfaces.vo.OrderInfoPageVO;
import com.roncoo.education.user.feign.interfaces.vo.OrderInfoViewVO;

/**
 * 订单信息表 接口
 *
 * @author wujing
 * @date 2022-09-06
 */
@FeignClient(value = "user-service", path = "/user/order/info")
public interface IFeignOrderInfo {

    /**
     * 分页列出-订单信息表
     *
     * @param qo 订单信息表
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<OrderInfoPageVO> page(@RequestBody OrderInfoPageQO qo);

    /**
     * 保存-订单信息表
     *
     * @param qo 订单信息表
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody OrderInfoSaveQO qo);

    /**
     * 根据ID删除-订单信息表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改订单信息表
     *
     * @param qo 订单信息表
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody OrderInfoEditQO qo);

    /**
     * 根据ID获取订单信息表
     *
     * @param id 主键ID
     * @return 订单信息表
     */
    @GetMapping(value = "/get/{id}")
    OrderInfoViewVO getById(@PathVariable(value = "id") Long id);

}
