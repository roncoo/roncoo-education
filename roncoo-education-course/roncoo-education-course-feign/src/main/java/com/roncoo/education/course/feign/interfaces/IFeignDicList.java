package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.DicListQO;
import com.roncoo.education.course.feign.interfaces.vo.DicListVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据字典明细表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignDicList {

    @RequestMapping(value = "/feign/course/dicList/listForPage", method = RequestMethod.POST)
    Page<DicListVO> listForPage(@RequestBody DicListQO qo);

    @RequestMapping(value = "/feign/course/dicList/save", method = RequestMethod.POST)
    int save(@RequestBody DicListQO qo);

    @RequestMapping(value = "/feign/course/dicList/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/dicList/update", method = RequestMethod.PUT)
    int updateById(@RequestBody DicListQO qo);

    @RequestMapping(value = "/feign/course/dicList/get/{id}", method = RequestMethod.GET)
    DicListVO getById(@PathVariable(value = "id") Long id);

}
