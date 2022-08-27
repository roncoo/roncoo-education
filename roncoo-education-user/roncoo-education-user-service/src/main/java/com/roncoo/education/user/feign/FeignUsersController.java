package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignUsersBiz;
import com.roncoo.education.user.feign.interfaces.IFeignUsers;
import com.roncoo.education.user.feign.interfaces.qo.UsersEditQO;
import com.roncoo.education.user.feign.interfaces.qo.UsersPageQO;
import com.roncoo.education.user.feign.interfaces.qo.UsersSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.UsersPageVO;
import com.roncoo.education.user.feign.interfaces.vo.UsersViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 用户信息
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/users")
public class FeignUsersController extends BaseController implements IFeignUsers {

    @NotNull
    private final FeignUsersBiz biz;

    @Override
    public Page<UsersPageVO> page(@RequestBody UsersPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody UsersSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody UsersEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public UsersViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
