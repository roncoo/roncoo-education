package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.biz.FeignMsgBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 站内信息表
 *
 * @author wuyun
 */
@RestController
public class FeignMsgController extends BaseController implements IFeignMsg {

    @Autowired
    private FeignMsgBiz biz;

    @Override
    public Page<MsgVO> listForPage(@RequestBody MsgQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody MsgQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody MsgQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public MsgVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

    @Override
    public int push() {
        return biz.push();
    }

    @Override
    public int pushByManual(@RequestBody Long id) {
        return biz.pushByManual(id);
    }
}
