package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignMsgTemplateBiz;
import com.roncoo.education.user.feign.interfaces.IFeignMsgTemplate;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplateEditQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplatePageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplateSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgTemplatePageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgTemplateViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 消息模板
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/msg/template")
public class FeignMsgTemplateController extends BaseController implements IFeignMsgTemplate {

    @NotNull
    private final FeignMsgTemplateBiz biz;

    @Override
    public Page<MsgTemplatePageVO> page(@RequestBody MsgTemplatePageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody MsgTemplateSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody MsgTemplateEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public MsgTemplateViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
