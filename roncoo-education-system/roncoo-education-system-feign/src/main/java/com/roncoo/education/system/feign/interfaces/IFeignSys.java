package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.SysQO;
import com.roncoo.education.system.feign.interfaces.vo.SysVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统配置表
 *
 * @author YZJ
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignSys {


    @RequestMapping(value = "/feign/system/sys/listForPage", method = RequestMethod.POST)
    Page<SysVO> listForPage(@RequestBody SysQO qo);

    @RequestMapping(value = "/feign/system/sys/save", method = RequestMethod.POST)
    int save(@RequestBody SysQO qo);

    @RequestMapping(value = "/feign/system/sys/deleteById", method = RequestMethod.DELETE)
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/sys/updateById", method = RequestMethod.PUT)
    int updateById(@RequestBody SysQO qo);

    @RequestMapping(value = "/feign/system/sys/getById", method = RequestMethod.GET)
    SysVO getById(@RequestBody Long id);

    /**
     * 获得系统配置表信息
     *
     * @return
     * @author YZJ
     */
    @RequestMapping(value = "/feign/system/sys/getSys", method = RequestMethod.POST)
    SysVO getSys();

}
