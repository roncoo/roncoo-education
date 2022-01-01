package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.MsgQO;
import com.roncoo.education.system.feign.interfaces.vo.MsgVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 站内信息表
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignMsg {


    @RequestMapping(value = "/feign/system/msg/listForPage")
    Page<MsgVO> listForPage(@RequestBody MsgQO qo);

    @RequestMapping(value = "/feign/system/msg/save")
    int save(@RequestBody MsgQO qo);

    @RequestMapping(value = "/feign/system/msg/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/msg/updateById")
    int updateById(@RequestBody MsgQO qo);

    @RequestMapping(value = "/feign/system/msg/getById")
    MsgVO getById(@RequestBody Long id);

    /**
     * 手动推送站内信
     *
     * @return
     * @author wuyun
     */
    @RequestMapping(value = "/feign/system/msg/pushByManual")
    int pushByManual(@RequestBody Long id);

    /**
     * 定时器推送站内信
     *
     * @return
     * @author wuyun
     */
    @RequestMapping(value = "/feign/system/msg/push")
    int push();

}
