package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsSaveQO;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsEditQO;
import com.roncoo.education.user.feign.interfaces.vo.LogSmsPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LogSmsViewVO;

/**
 * 用户短信发送日志 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "user-service", path = "/user/log/sms")
public interface IFeignLogSms {

    /**
     * 分页列出-用户短信发送日志
     *
     * @param qo 用户短信发送日志
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<LogSmsPageVO> page(@RequestBody LogSmsPageQO qo);

    /**
     * 保存-用户短信发送日志
     *
     * @param qo 用户短信发送日志
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody LogSmsSaveQO qo);

    /**
     * 根据ID删除-用户短信发送日志
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改用户短信发送日志
     *
     * @param qo 用户短信发送日志
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody LogSmsEditQO qo);

    /**
     * 根据ID获取用户短信发送日志
     *
     * @param id 主键ID
     * @return 用户短信发送日志
     */
    @GetMapping(value = "/get/{id}")
    LogSmsViewVO getById(@PathVariable(value = "id") Long id);
}
