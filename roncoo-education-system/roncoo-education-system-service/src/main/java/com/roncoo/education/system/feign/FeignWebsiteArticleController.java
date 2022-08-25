package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignWebsiteArticleBiz;
import com.roncoo.education.system.feign.interfaces.IFeignWebsiteArticle;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticleEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticlePageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteArticleSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteArticlePageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteArticleViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 站点导航文章
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/website/article")
public class FeignWebsiteArticleController implements IFeignWebsiteArticle {

    @NotNull
    private final FeignWebsiteArticleBiz biz;

    @Override
    public Page
            <WebsiteArticlePageVO> page(@RequestBody WebsiteArticlePageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody WebsiteArticleSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody WebsiteArticleEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public WebsiteArticleViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
