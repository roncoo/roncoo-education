package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.biz.FeignPlatformBiz;
import com.roncoo.education.user.feign.interfaces.IFeignPlatform;
import com.roncoo.education.user.feign.interfaces.qo.PlatformQO;
import com.roncoo.education.user.feign.interfaces.vo.PlatformVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台信息
 *
 * @author wujing
 */
@RestController
public class FeignPlatformController extends BaseController implements IFeignPlatform {

    @Autowired
    private FeignPlatformBiz biz;

    @Override
    public Page<PlatformVO> listForPage(@RequestBody PlatformQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody PlatformQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody PlatformQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public PlatformVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public PlatformVO getByClientId(@PathVariable(value = "clientId") String clientId) {
        return biz.getByClientId(clientId);
    }

}
