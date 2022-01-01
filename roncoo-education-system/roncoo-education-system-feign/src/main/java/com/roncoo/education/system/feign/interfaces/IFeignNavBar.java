package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.NavBarQO;
import com.roncoo.education.system.feign.interfaces.vo.NavBarVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 头部导航
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignNavBar {

    @RequestMapping(value = "/feign/system/navBar/listForPage", method = RequestMethod.POST)
    Page<NavBarVO> listForPage(@RequestBody NavBarQO qo);

    @RequestMapping(value = "/feign/system/navBar/save", method = RequestMethod.POST)
    int save(@RequestBody NavBarQO qo);

    @RequestMapping(value = "/feign/system/navBar/deleteById", method = RequestMethod.DELETE)
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/navBar/updateById", method = RequestMethod.PUT)
    int updateById(@RequestBody NavBarQO qo);

    @RequestMapping(value = "/feign/system/navBar/get/{id}", method = RequestMethod.GET)
    NavBarVO getById(@PathVariable(value = "id") Long id);

}
