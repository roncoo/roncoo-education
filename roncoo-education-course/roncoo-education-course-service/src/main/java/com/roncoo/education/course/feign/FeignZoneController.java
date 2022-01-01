package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignZoneBiz;
import com.roncoo.education.course.feign.interfaces.IFeignZone;
import com.roncoo.education.course.feign.interfaces.qo.ZoneQO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 专区
 *
 * @author wujing
 */
@RestController
public class FeignZoneController extends BaseController implements IFeignZone {

    @Autowired
    private FeignZoneBiz biz;

    @Override
    public Page<ZoneVO> listForPage(@RequestBody ZoneQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody ZoneQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody ZoneQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public ZoneVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public List<ZoneVO> listAllZone() {
        return biz.listAllZone();
    }

}
