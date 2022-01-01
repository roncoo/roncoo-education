package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.SysLogQO;
import com.roncoo.education.system.feign.interfaces.vo.SysLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignSysLog {


    @RequestMapping(value = "/feign/system/sysLog/listForPage")
    Page<SysLogVO> listForPage(@RequestBody SysLogQO qo);

    @RequestMapping(value = "/feign/system/sysLog/save")
    int save(@RequestBody SysLogQO qo);

    @RequestMapping(value = "/feign/system/sysLog/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/sysLog/updateById")
    int updateById(@RequestBody SysLogQO qo);

    @RequestMapping(value = "/feign/system/sysLog/getById")
    SysLogVO getById(@RequestBody Long id);

}
