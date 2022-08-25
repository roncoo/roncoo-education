package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigEditQO;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigPageQO;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigPageVO;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigViewVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "system-service", path = "/system/sys/config")
public interface IFeignSysConfig {

    /**
     * 分页列出-系统配置
     *
     * @param qo 系统配置
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<SysConfigPageVO> page(@RequestBody SysConfigPageQO qo);

    /**
     * 保存-系统配置
     *
     * @param qo 系统配置
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody SysConfigSaveQO qo);

    /**
     * 根据ID删除-系统配置
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改系统配置
     *
     * @param qo 系统配置
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody SysConfigEditQO qo);

    /**
     * 根据ID获取系统配置
     *
     * @param id 主键ID
     * @return 系统配置
     */
    @GetMapping(value = "/get/{id}")
    SysConfigViewVO getById(@PathVariable(value = "id") Long id);
}