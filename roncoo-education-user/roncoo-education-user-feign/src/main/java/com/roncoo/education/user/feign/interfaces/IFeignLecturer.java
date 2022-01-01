package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.LecturerQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 讲师信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignLecturer {

    @RequestMapping(value = "/feign/user/lecturer/listForPage", method = RequestMethod.POST)
    Page<LecturerVO> listForPage(@RequestBody LecturerQO qo);

    @RequestMapping(value = "/feign/user/lecturer/save", method = RequestMethod.POST)
    int save(@RequestBody LecturerQO qo);

    @RequestMapping(value = "/feign/user/lecturer/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/lecturer/update", method = RequestMethod.PUT)
    int updateById(@RequestBody LecturerQO qo);

    @RequestMapping(value = "/feign/user/lecturer/get/{id}", method = RequestMethod.GET)
    LecturerVO getById(@PathVariable(value = "id") Long id);

    /***
     * 根据讲师用户编号查找讲师信息
     */
    @RequestMapping(value = "/feign/user/lecturer/getByLecturerUserNo/{lecturerUserNo}", method = RequestMethod.GET)
    LecturerVO getByLecturerUserNo(@PathVariable(value = "lecturerUserNo") Long lecturerUserNo);

    /**
     * 列出所有讲师信息
     *
     * @author LHR
     */
    @RequestMapping(value = "/feign/user/lecturer/listAllForLecturer", method = RequestMethod.POST)
    List<LecturerVO> listAllForLecturer();

}
