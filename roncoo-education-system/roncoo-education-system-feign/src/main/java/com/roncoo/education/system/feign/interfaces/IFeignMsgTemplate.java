package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.system.feign.qo.MsgTemplateQO;
import com.roncoo.education.system.feign.vo.MsgTemplateVO;
import com.roncoo.education.util.base.Page;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 消息模板
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignMsgTemplate {

    @RequestMapping(value = "/feign/system/msgTemplate/listForPage")
    Page<MsgTemplateVO> listForPage(@RequestBody MsgTemplateQO qo);

    @RequestMapping(value = "/feign/system/msgTemplate/save")
    int save(@RequestBody MsgTemplateQO qo);

    @RequestMapping(value = "/feign/system/msgTemplate/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/msgTemplate/updateById")
    int updateById(@RequestBody MsgTemplateQO qo);

    @RequestMapping(value = "/feign/system/msgTemplate/getById")
    MsgTemplateVO getById(@RequestBody Long id);

}
