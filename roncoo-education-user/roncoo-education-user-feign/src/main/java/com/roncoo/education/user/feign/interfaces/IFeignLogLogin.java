package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginSaveQO;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginEditQO;
import com.roncoo.education.user.feign.interfaces.vo.LogLoginPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LogLoginViewVO;

/**
 * 用户登录日志 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "user-service", path = "/user/log/login")
public interface IFeignLogLogin {

    /**
     * 分页列出-用户登录日志
     *
     * @param qo 用户登录日志
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<LogLoginPageVO> page(@RequestBody LogLoginPageQO qo);

    /**
     * 保存-用户登录日志
     *
     * @param qo 用户登录日志
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody LogLoginSaveQO qo);

    /**
     * 根据ID删除-用户登录日志
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改用户登录日志
     *
     * @param qo 用户登录日志
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody LogLoginEditQO qo);

    /**
     * 根据ID获取用户登录日志
     *
     * @param id 主键ID
     * @return 用户登录日志
     */
    @GetMapping(value = "/get/{id}")
    LogLoginViewVO getById(@PathVariable(value = "id") Long id);

}
