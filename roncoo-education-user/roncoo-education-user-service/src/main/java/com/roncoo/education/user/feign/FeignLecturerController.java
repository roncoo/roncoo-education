package com.roncoo.education.user.feign;

import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignLecturerBiz;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.vo.LecturerViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 讲师信息
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/lecturer")
public class FeignLecturerController extends BaseController implements IFeignLecturer {

    @NotNull
    private final FeignLecturerBiz biz;

    @Override
    public LecturerViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public Map<Long, String> listByIds(@RequestBody List<Long> lecturerIdList) {
        return biz.listByIds(lecturerIdList);
    }
}
