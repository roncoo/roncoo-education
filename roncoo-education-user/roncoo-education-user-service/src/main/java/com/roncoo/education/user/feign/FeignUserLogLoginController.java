package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.biz.FeignUserLogLoginBiz;
import com.roncoo.education.user.feign.interfaces.IFeignUserLogLogin;
import com.roncoo.education.user.feign.interfaces.qo.UserLogLoginQO;
import com.roncoo.education.user.feign.interfaces.vo.UserLogLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户错误登录日志
 *
 * @author wujing
 */
@RestController
public class FeignUserLogLoginController extends BaseController implements IFeignUserLogLogin {

    @Autowired
    private FeignUserLogLoginBiz biz;

    @Override
    public Page<UserLogLoginVO> listForPage(@RequestBody UserLogLoginQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody UserLogLoginQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody UserLogLoginQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public UserLogLoginVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
