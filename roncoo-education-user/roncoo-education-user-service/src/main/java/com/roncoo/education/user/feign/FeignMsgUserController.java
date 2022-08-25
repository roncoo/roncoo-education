package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.biz.FeignMsgUserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 站内信用户记录表
 *
 * @author wuyun
 */
@RestController
public class FeignMsgUserController extends BaseController implements IFeignMsgUser {

    @Autowired
    private FeignMsgUserBiz biz;

    @Override
    public Page<MsgUserVO> listForPage(@RequestBody MsgUserQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody MsgUserQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody MsgUserQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public MsgUserVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
