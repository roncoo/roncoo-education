package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignLogLoginBiz;
import com.roncoo.education.user.feign.interfaces.IFeignLogLogin;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginEditQO;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.LogLoginPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LogLoginViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 用户登录日志
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/log/login")
public class FeignLogLoginController extends BaseController implements IFeignLogLogin {

    @NotNull
    private final FeignLogLoginBiz biz;

    @Override
    public Page<LogLoginPageVO> page(@RequestBody LogLoginPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody LogLoginSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody LogLoginEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public LogLoginViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
