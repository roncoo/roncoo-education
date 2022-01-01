package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.LecturerExtQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerExtVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 讲师账户信息表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignLecturerExt {

    @RequestMapping(value = "/feign/user/lecturerExt/listForPage", method = RequestMethod.POST)
    Page<LecturerExtVO> listForPage(@RequestBody LecturerExtQO qo);

    @RequestMapping(value = "/feign/user/lecturerExt/save", method = RequestMethod.POST)
    int save(@RequestBody LecturerExtQO qo);

    @RequestMapping(value = "/feign/user/lecturerExt/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/lecturerExt/update", method = RequestMethod.PUT)
    int updateById(@RequestBody LecturerExtQO qo);

    @RequestMapping(value = "/feign/user/lecturerExt/get/{id}", method = RequestMethod.GET)
    LecturerExtVO getById(@PathVariable(value = "id") Long id);

    /**
     * 根据讲师编号查找账户信息（订单回调远程调用）
     *
     * @param lecturerUserNo
     * @return
     * @author wuyun
     */
    @RequestMapping(value = "/feign/user/lecturerExt/getByLecturerUserNo/{lecturerUserNo}", method = RequestMethod.GET)
    LecturerExtVO getByLecturerUserNo(@PathVariable(value = "lecturerUserNo") Long lecturerUserNo);

    /**
     * 更新账户的金额信息(订单回调远程调用)
     *
     * @param qo
     * @return
     * @author wuyun
     */
    @RequestMapping(value = "/feign/user/lecturerExt/updateTotalIncomeByLecturerUserNo", method = RequestMethod.PUT)
    void updateTotalIncomeByLecturerUserNo(@RequestBody LecturerExtQO qo);

}
