package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignWebsiteNavBiz;
import com.roncoo.education.system.feign.interfaces.IFeignWebsiteNav;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavPageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavPageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 头部导航
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/website/nav")
public class FeignWebsiteNavController implements IFeignWebsiteNav {

    @NotNull
    private final FeignWebsiteNavBiz biz;

    @Override
    public Page
            <WebsiteNavPageVO> page(@RequestBody WebsiteNavPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody WebsiteNavSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody WebsiteNavEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public WebsiteNavViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
