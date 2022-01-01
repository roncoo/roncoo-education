package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.UserQO;
import com.roncoo.education.user.feign.interfaces.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignUser {

    @RequestMapping(value = "/feign/user/user/listForPage", method = RequestMethod.POST)
    Page<UserVO> listForPage(@RequestBody UserQO qo);

    @RequestMapping(value = "/feign/user/user/save", method = RequestMethod.POST)
    int save(@RequestBody UserQO qo);

    @RequestMapping(value = "/feign/user/user/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/user/update", method = RequestMethod.PUT)
    int updateById(@RequestBody UserQO qo);

    @RequestMapping(value = "/feign/user/user/get/{id}", method = RequestMethod.GET)
    UserVO getById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/user/getByUserNo/{userNo}", method = RequestMethod.GET)
    UserVO getByUserNo(@PathVariable(value = "userNo") Long userNo);

    @RequestMapping(value = "/feign/user/user/getByMobile/{mobile}", method = RequestMethod.GET)
    UserVO getByMobile(@PathVariable(value = "mobile") String mobile);

}
