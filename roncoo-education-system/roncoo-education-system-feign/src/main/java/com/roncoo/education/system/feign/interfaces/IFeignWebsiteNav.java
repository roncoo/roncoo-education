package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavPageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavPageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavViewVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 头部导航 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "system-service", path = "/system/website/nav")
public interface IFeignWebsiteNav {

    /**
     * 分页列出-头部导航
     *
     * @param qo 头部导航
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<WebsiteNavPageVO> page(@RequestBody WebsiteNavPageQO qo);

    /**
     * 保存-头部导航
     *
     * @param qo 头部导航
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody WebsiteNavSaveQO qo);

    /**
     * 根据ID删除-头部导航
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改头部导航
     *
     * @param qo 头部导航
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody WebsiteNavEditQO qo);

    /**
     * 根据ID获取头部导航
     *
     * @param id 主键ID
     * @return 头部导航
     */
    @GetMapping(value = "/get/{id}")
    WebsiteNavViewVO getById(@PathVariable(value = "id") Long id);
}