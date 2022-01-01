package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.UserLogLoginQO;
import com.roncoo.education.user.feign.interfaces.vo.UserLogLoginVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户错误登录日志
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignUserLogLogin {

    @RequestMapping(value = "/boss/user/userLogLogin/listForPage", method = RequestMethod.POST)
    Page<UserLogLoginVO> listForPage(@RequestBody UserLogLoginQO qo);

    @RequestMapping(value = "/boss/user/userLogLogin/save", method = RequestMethod.POST)
    int save(@RequestBody UserLogLoginQO qo);

    @RequestMapping(value = "/boss/user/userLogLogin/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/boss/user/userLogLogin/update", method = RequestMethod.PUT)
    int updateById(@RequestBody UserLogLoginQO qo);

    @RequestMapping(value = "/boss/user/userLogLogin/get/{id}", method = RequestMethod.GET)
    UserLogLoginVO getById(@PathVariable(value = "id") Long id);

}
