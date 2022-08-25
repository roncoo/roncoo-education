package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignLecturerBiz;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.qo.LecturerEditQO;
import com.roncoo.education.user.feign.interfaces.qo.LecturerPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LecturerSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 讲师信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/lecturer")
public class FeignLecturerController extends BaseController implements IFeignLecturer {

    @NotNull
    private final FeignLecturerBiz biz;

    @Override
    public Page<LecturerPageVO> page(@RequestBody LecturerPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody LecturerSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody LecturerEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public LecturerViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
