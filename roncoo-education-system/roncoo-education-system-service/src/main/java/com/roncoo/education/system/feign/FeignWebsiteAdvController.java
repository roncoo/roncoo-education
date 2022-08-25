package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignWebsiteAdvBiz;
import com.roncoo.education.system.feign.interfaces.IFeignWebsiteAdv;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvPageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteAdvPageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteAdvViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 广告信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/website/adv")
public class FeignWebsiteAdvController implements IFeignWebsiteAdv {

    @NotNull
    private final FeignWebsiteAdvBiz biz;

    @Override
    public Page
            <WebsiteAdvPageVO> page(@RequestBody WebsiteAdvPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody WebsiteAdvSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody WebsiteAdvEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public WebsiteAdvViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
