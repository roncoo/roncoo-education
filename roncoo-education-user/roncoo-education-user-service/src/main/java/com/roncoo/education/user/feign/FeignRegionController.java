package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignRegionBiz;
import com.roncoo.education.user.feign.interfaces.IFeignRegion;
import com.roncoo.education.user.feign.interfaces.qo.RegionEditQO;
import com.roncoo.education.user.feign.interfaces.qo.RegionPageQO;
import com.roncoo.education.user.feign.interfaces.qo.RegionSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.RegionPageVO;
import com.roncoo.education.user.feign.interfaces.vo.RegionViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 行政区域表
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/region")
public class FeignRegionController extends BaseController implements IFeignRegion {

    @NotNull
    private final FeignRegionBiz biz;

    @Override
    public Page<RegionPageVO> page(@RequestBody RegionPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody RegionSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody RegionEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public RegionViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
