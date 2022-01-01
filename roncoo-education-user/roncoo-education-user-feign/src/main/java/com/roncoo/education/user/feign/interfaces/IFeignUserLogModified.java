package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.UserLogModifiedQO;
import com.roncoo.education.user.feign.interfaces.vo.UserLogModifiedVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户修改日志
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignUserLogModified {

    @RequestMapping(value = "/feign/user/userLogModified/listForPage", method = RequestMethod.POST)
    Page<UserLogModifiedVO> listForPage(@RequestBody UserLogModifiedQO qo);

    @RequestMapping(value = "/feign/user/userLogModified/save", method = RequestMethod.POST)
    int save(@RequestBody UserLogModifiedQO qo);

    @RequestMapping(value = "/feign/user/userLogModified/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/userLogModified/update", method = RequestMethod.PUT)
    int updateById(@RequestBody UserLogModifiedQO qo);

    @RequestMapping(value = "/feign/user/userLogModified/get/{id}", method = RequestMethod.GET)
    UserLogModifiedVO getById(@PathVariable(value = "id") Long id);

}
