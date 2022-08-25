package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignLogSmsBiz;
import com.roncoo.education.user.feign.interfaces.IFeignLogSms;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsEditQO;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.LogSmsPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LogSmsViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 用户短信发送日志
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/log/sms")
public class FeignLogSmsController extends BaseController implements IFeignLogSms {

    @NotNull
    private final FeignLogSmsBiz biz;

    @Override
    public Page<LogSmsPageVO> page(@RequestBody LogSmsPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody LogSmsSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody LogSmsEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public LogSmsViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
