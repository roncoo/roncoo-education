package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignMsgBiz;
import com.roncoo.education.user.feign.interfaces.IFeignMsg;
import com.roncoo.education.user.feign.interfaces.qo.MsgEditQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgPageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgPageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 站内信息表
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/msg")
public class FeignMsgController extends BaseController implements IFeignMsg {

    @NotNull
    private final FeignMsgBiz biz;

    @Override
    public Page<MsgPageVO> page(@RequestBody MsgPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody MsgSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody MsgEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public MsgViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
