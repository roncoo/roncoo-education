package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.PlatformQO;
import com.roncoo.education.user.feign.interfaces.vo.PlatformVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 平台信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignPlatform {

    @RequestMapping(value = "/feign/user/platform/listForPage", method = RequestMethod.POST)
    Page<PlatformVO> listForPage(@RequestBody PlatformQO qo);

    @RequestMapping(value = "/feign/user/platform/save", method = RequestMethod.POST)
    int save(@RequestBody PlatformQO qo);

    @RequestMapping(value = "/feign/user/platform/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/platform/update", method = RequestMethod.PUT)
    int updateById(@RequestBody PlatformQO qo);

    @RequestMapping(value = "/feign/user/platform/get/{id}", method = RequestMethod.GET)
    PlatformVO getById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/platform/getByClientId/{clientId}", method = RequestMethod.GET)
    PlatformVO getByClientId(@PathVariable(value = "clientId") String clientId);

}
