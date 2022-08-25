package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplatePageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplateSaveQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplateEditQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgTemplatePageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgTemplateViewVO;

/**
 * 消息模板 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "user-service", path = "/user/msg/template")
public interface IFeignMsgTemplate {

    /**
     * 分页列出-消息模板
     *
     * @param qo 消息模板
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<MsgTemplatePageVO> page(@RequestBody MsgTemplatePageQO qo);

    /**
     * 保存-消息模板
     *
     * @param qo 消息模板
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody MsgTemplateSaveQO qo);

    /**
     * 根据ID删除-消息模板
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改消息模板
     *
     * @param qo 消息模板
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody MsgTemplateEditQO qo);

    /**
     * 根据ID获取消息模板
     *
     * @param id 主键ID
     * @return 消息模板
     */
    @GetMapping(value = "/get/{id}")
    MsgTemplateViewVO getById(@PathVariable(value = "id") Long id);
}
