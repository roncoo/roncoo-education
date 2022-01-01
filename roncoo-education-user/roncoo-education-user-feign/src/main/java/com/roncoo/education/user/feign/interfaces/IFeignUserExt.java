package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.UserExtEchartsQO;
import com.roncoo.education.user.feign.interfaces.qo.UserExtQO;
import com.roncoo.education.user.feign.interfaces.vo.UserEchartsVO;
import com.roncoo.education.user.feign.interfaces.vo.UserExtVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignUserExt {

    @RequestMapping(value = "/feign/user/userExt/listForPage", method = RequestMethod.POST)
    Page<UserExtVO> listForPage(@RequestBody UserExtQO qo);

    @RequestMapping(value = "/feign/user/userExt/save", method = RequestMethod.POST)
    int save(@RequestBody UserExtQO qo);

    @RequestMapping(value = "/feign/user/userExt/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/userExt/update", method = RequestMethod.PUT)
    int updateById(@RequestBody UserExtQO qo);

    @RequestMapping(value = "/feign/user/userExt/get/{id}", method = RequestMethod.GET)
    UserExtVO getById(@PathVariable(value = "id") Long id);

    /**
     * 根据userNo查找用户教育信息
     */
    @RequestMapping(value = "/feign/user/userExt/get/userNo/{userNo}", method = RequestMethod.GET)
    UserExtVO getByUserNo(@PathVariable(value = "userNo") Long userNo);

    /**
     * 获取用户注册量
     *
     * @param userExtEchartsQO
     * @return
     * @author wuyun
     */
    @RequestMapping(value = "/feign/user/userExt/sumByCounts", method = RequestMethod.POST)
    List<UserEchartsVO> sumByCounts(@RequestBody UserExtEchartsQO userExtEchartsQO);

    /**
     * 批量缓存用户信息,发送站内信用
     *
     * @author wuyun
     */
    @RequestMapping(value = "/feign/user/userExt/cachUserForMsg")
    void cachUserForMsg();

}
