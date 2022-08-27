package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignZoneBiz;
import com.roncoo.education.course.feign.interfaces.IFeignZone;
import com.roncoo.education.course.feign.interfaces.qo.ZoneEditQO;
import com.roncoo.education.course.feign.interfaces.qo.ZonePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.ZonePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 专区
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/zone")
public class FeignZoneController extends BaseController implements IFeignZone {

    @NotNull
    private final FeignZoneBiz biz;

    @Override
    public Page<ZonePageVO> page(@RequestBody ZonePageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody ZoneSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody ZoneEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public ZoneViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
