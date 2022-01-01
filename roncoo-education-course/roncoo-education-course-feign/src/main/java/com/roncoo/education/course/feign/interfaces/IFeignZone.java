package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.ZoneQO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 专区
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignZone {

    @RequestMapping(value = "/feign/course/zone/listForPage", method = RequestMethod.POST)
    Page<ZoneVO> listForPage(@RequestBody ZoneQO qo);

    @RequestMapping(value = "/feign/course/zone/save", method = RequestMethod.POST)
    int save(@RequestBody ZoneQO qo);

    @RequestMapping(value = "/feign/course/zone/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/zone/update", method = RequestMethod.PUT)
    int updateById(@RequestBody ZoneQO qo);

    @RequestMapping(value = "/feign/course/zone/get/{id}", method = RequestMethod.GET)
    ZoneVO getById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/zone/listAllZone", method = RequestMethod.GET)
    List<ZoneVO> listAllZone();
}
