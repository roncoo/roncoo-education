package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvPageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteAdvPageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteAdvViewVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 广告信息 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "system-service", path = "/system/website/adv")
public interface IFeignWebsiteAdv {

    /**
     * 分页列出-广告信息
     *
     * @param qo 广告信息
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<WebsiteAdvPageVO> page(@RequestBody WebsiteAdvPageQO qo);

    /**
     * 保存-广告信息
     *
     * @param qo 广告信息
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody WebsiteAdvSaveQO qo);

    /**
     * 根据ID删除-广告信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改广告信息
     *
     * @param qo 广告信息
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody WebsiteAdvEditQO qo);

    /**
     * 根据ID获取广告信息
     *
     * @param id 主键ID
     * @return 广告信息
     */
    @GetMapping(value = "/get/{id}")
    WebsiteAdvViewVO getById(@PathVariable(value = "id") Long id);
}