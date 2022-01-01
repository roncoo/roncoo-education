package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 站点信息
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignWebsite {

    @RequestMapping(value = "/feign/system/website/listForPage", method = RequestMethod.POST)
    Page<WebsiteVO> listForPage(@RequestBody WebsiteQO qo);

    @RequestMapping(value = "/feign/system/website/save", method = RequestMethod.POST)
    int save(@RequestBody WebsiteQO qo);

    @RequestMapping(value = "/feign/system/website/deleteById", method = RequestMethod.DELETE)
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/website/updateById", method = RequestMethod.PUT)
    int updateById(@RequestBody WebsiteQO qo);

    @RequestMapping(value = "/feign/system/website/get/{id}", method = RequestMethod.GET)
    WebsiteVO getById(@PathVariable(value = "id") Long id);

    /**
     * 获取站点信息
     *
     * @author wuyun
     */
    @RequestMapping(value = "/feign/system/website/getWebsite", method = RequestMethod.GET)
    WebsiteVO getWebsite();
}
