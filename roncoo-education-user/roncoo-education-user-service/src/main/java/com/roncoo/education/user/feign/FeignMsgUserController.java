package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignMsgUserBiz;
import com.roncoo.education.user.feign.interfaces.IFeignMsgUser;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserEditQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserPageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgUserPageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgUserViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 站内信用户记录表
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/msg/user")
public class FeignMsgUserController extends BaseController implements IFeignMsgUser {

    @NotNull
    private final FeignMsgUserBiz biz;

    @Override
    public Page<MsgUserPageVO> page(@RequestBody MsgUserPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody MsgUserSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody MsgUserEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public MsgUserViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
