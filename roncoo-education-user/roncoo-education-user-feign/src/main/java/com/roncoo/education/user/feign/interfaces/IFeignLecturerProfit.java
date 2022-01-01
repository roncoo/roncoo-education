package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.LecturerProfitQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerProfitVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 讲师提现日志表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IFeignLecturerProfit {

    @RequestMapping(value = "/feign/user/lecturerProfit/listForPage", method = RequestMethod.POST)
    Page<LecturerProfitVO> listForPage(@RequestBody LecturerProfitQO qo);

    @RequestMapping(value = "/feign/user/lecturerProfit/save", method = RequestMethod.POST)
    int save(@RequestBody LecturerProfitQO qo);

    @RequestMapping(value = "/feign/user/lecturerProfit/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/user/lecturerProfit/update", method = RequestMethod.PUT)
    int updateById(@RequestBody LecturerProfitQO qo);

    @RequestMapping(value = "/feign/user/lecturerProfit/get/{id}", method = RequestMethod.GET)
    LecturerProfitVO getById(@PathVariable(value = "id") Long id);

}
