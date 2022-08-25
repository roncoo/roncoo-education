package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.MsgPageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgSaveQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgEditQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgPageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgViewVO;

/**
 * 站内信息表 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "user-service", path = "/user/msg")
public interface IFeignMsg {

    /**
     * 分页列出-站内信息表
     *
     * @param qo 站内信息表
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<MsgPageVO> page(@RequestBody MsgPageQO qo);

    /**
     * 保存-站内信息表
     *
     * @param qo 站内信息表
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody MsgSaveQO qo);

    /**
     * 根据ID删除-站内信息表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改站内信息表
     *
     * @param qo 站内信息表
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody MsgEditQO qo);

    /**
     * 根据ID获取站内信息表
     *
     * @param id 主键ID
     * @return 站内信息表
     */
    @GetMapping(value = "/get/{id}")
    MsgViewVO getById(@PathVariable(value = "id") Long id);
}
