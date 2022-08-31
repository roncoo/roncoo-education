package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.UsersPageQO;
import com.roncoo.education.user.feign.interfaces.qo.UsersSaveQO;
import com.roncoo.education.user.feign.interfaces.qo.UsersEditQO;
import com.roncoo.education.user.feign.interfaces.vo.UsersPageVO;
import com.roncoo.education.user.feign.interfaces.vo.UsersViewVO;

/**
 * 用户信息 接口
 *
 * @author wujing
 * @date 2022-08-31
 */
@FeignClient(value = "user-service", path = "/user/users")
public interface IFeignUsers {

    /**
     * 分页列出-用户信息
     *
     * @param qo 用户信息
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<UsersPageVO> page(@RequestBody UsersPageQO qo);

    /**
     * 保存-用户信息
     *
     * @param qo 用户信息
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody UsersSaveQO qo);

    /**
     * 根据ID删除-用户信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改用户信息
     *
     * @param qo 用户信息
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody UsersEditQO qo);

    /**
     * 根据ID获取用户信息
     *
     * @param id 主键ID
     * @return 用户信息
     */
    @GetMapping(value = "/get/{id}")
    UsersViewVO getById(@PathVariable(value = "id") Long id);

}
