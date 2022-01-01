package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.SendSmsLogQO;
import com.roncoo.education.user.feign.interfaces.vo.SendSmsLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignSendSmsLog {

    @RequestMapping(value = "/feign/user/sendSmsLog/listForPage")
    Page<SendSmsLogVO> listForPage(@RequestBody SendSmsLogQO qo);

    @RequestMapping(value = "/feign/user/sendSmsLog/save")
    int save(@RequestBody SendSmsLogQO qo);

    @RequestMapping(value = "/feign/user/sendSmsLog/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/user/sendSmsLog/updateById")
    int updateById(@RequestBody SendSmsLogQO qo);

    @RequestMapping(value = "/feign/user/sendSmsLog/getById")
    SendSmsLogVO getById(@RequestBody Long id);

    /**
     * 用户发送短信日志
     *
     * @param qo
     * @return
     * @author YZJ
     */
    @RequestMapping(value = "/feign/user/sendSmsLog/send")
    int send(@RequestBody SendSmsLogQO qo);

}
