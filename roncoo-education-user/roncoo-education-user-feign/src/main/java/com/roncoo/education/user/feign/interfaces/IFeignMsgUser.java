package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserPageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserSaveQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserEditQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgUserPageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgUserViewVO;

/**
 * 站内信用户记录表 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "user-service", path = "/user/msg/user")
public interface IFeignMsgUser {

    /**
     * 分页列出-站内信用户记录表
     *
     * @param qo 站内信用户记录表
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<MsgUserPageVO> page(@RequestBody MsgUserPageQO qo);

    /**
     * 保存-站内信用户记录表
     *
     * @param qo 站内信用户记录表
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody MsgUserSaveQO qo);

    /**
     * 根据ID删除-站内信用户记录表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改站内信用户记录表
     *
     * @param qo 站内信用户记录表
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody MsgUserEditQO qo);

    /**
     * 根据ID获取站内信用户记录表
     *
     * @param id 主键ID
     * @return 站内信用户记录表
     */
    @GetMapping(value = "/get/{id}")
    MsgUserViewVO getById(@PathVariable(value = "id") Long id);
}
