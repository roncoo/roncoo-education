package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 站点导航
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignWebsiteNav {


    @RequestMapping(value = "/feign/system/websiteNav/listForPage", method = RequestMethod.POST)
    Page<WebsiteNavVO> listForPage(@RequestBody WebsiteNavQO qo);

    @RequestMapping(value = "/feign/system/websiteNav/save", method = RequestMethod.POST)
    int save(@RequestBody WebsiteNavQO qo);

    @RequestMapping(value = "/feign/system/websiteNav/deleteById", method = RequestMethod.DELETE)
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/websiteNav/updateById", method = RequestMethod.PUT)
    int updateById(@RequestBody WebsiteNavQO qo);

    @RequestMapping(value = "/feign/system/websiteNav/get/{id}", method = RequestMethod.GET)
    WebsiteNavVO getById(@PathVariable(value = "id") Long id);

}
