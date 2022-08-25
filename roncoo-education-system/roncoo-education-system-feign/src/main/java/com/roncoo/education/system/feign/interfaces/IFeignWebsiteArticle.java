package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticleEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticlePageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticleSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteArticlePageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteArticleViewVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 站点导航文章 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "system-service", path = "/system/website/article")
public interface IFeignWebsiteArticle {

    /**
     * 分页列出-站点导航文章
     *
     * @param qo 站点导航文章
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<WebsiteArticlePageVO> page(@RequestBody WebsiteArticlePageQO qo);

    /**
     * 保存-站点导航文章
     *
     * @param qo 站点导航文章
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody WebsiteArticleSaveQO qo);

    /**
     * 根据ID删除-站点导航文章
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改站点导航文章
     *
     * @param qo 站点导航文章
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody WebsiteArticleEditQO qo);

    /**
     * 根据ID获取站点导航文章
     *
     * @param id 主键ID
     * @return 站点导航文章
     */
    @GetMapping(value = "/get/{id}")
    WebsiteArticleViewVO getById(@PathVariable(value = "id") Long id);
}