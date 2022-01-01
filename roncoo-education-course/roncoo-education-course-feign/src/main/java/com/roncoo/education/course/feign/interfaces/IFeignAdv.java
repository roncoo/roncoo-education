package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.AdvQO;
import com.roncoo.education.course.feign.interfaces.vo.AdvVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 广告信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignAdv {

    @RequestMapping(value = "/feign/course/adv/listForPage", method = RequestMethod.POST)
    Page<AdvVO> listForPage(@RequestBody AdvQO qo);

    @RequestMapping(value = "/feign/course/adv/save", method = RequestMethod.POST)
    int save(@RequestBody AdvQO qo);

    @RequestMapping(value = "/feign/course/adv/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/adv/update", method = RequestMethod.PUT)
    int updateById(@RequestBody AdvQO qo);

    @RequestMapping(value = "/feign/course/adv/get/{id}", method = RequestMethod.GET)
    AdvVO getById(@PathVariable(value = "id") Long id);

}
