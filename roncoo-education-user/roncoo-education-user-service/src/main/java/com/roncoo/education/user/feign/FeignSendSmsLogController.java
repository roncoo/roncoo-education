package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.biz.FeignSendSmsLogBiz;
import com.roncoo.education.user.feign.interfaces.IFeignSendSmsLog;
import com.roncoo.education.user.feign.interfaces.qo.SendSmsLogQO;
import com.roncoo.education.user.feign.interfaces.vo.SendSmsLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@RestController
public class FeignSendSmsLogController extends BaseController implements IFeignSendSmsLog {

    @Autowired
    private FeignSendSmsLogBiz biz;

    @Override
    public Page<SendSmsLogVO> listForPage(@RequestBody SendSmsLogQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SendSmsLogQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SendSmsLogQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SendSmsLogVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

    @Override
    public int send(@RequestBody SendSmsLogQO qo) {
        return biz.send(qo);
    }

}
