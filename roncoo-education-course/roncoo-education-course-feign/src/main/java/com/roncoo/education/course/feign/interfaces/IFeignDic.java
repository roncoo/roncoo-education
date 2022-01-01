package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.DicQO;
import com.roncoo.education.course.feign.interfaces.vo.DicVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据字典
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignDic {

    @RequestMapping(value = "/feign/course/dic/listForPage", method = RequestMethod.POST)
    Page<DicVO> listForPage(@RequestBody DicQO qo);

    @RequestMapping(value = "/feign/course/dic/save", method = RequestMethod.POST)
    int save(@RequestBody DicQO qo);

    @RequestMapping(value = "/feign/course/dic/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/dic/update", method = RequestMethod.PUT)
    int updateById(@RequestBody DicQO qo);

    @RequestMapping(value = "/feign/course/dic/get/{id}", method = RequestMethod.GET)
    DicVO getById(@PathVariable(value = "id") Long id);

}
