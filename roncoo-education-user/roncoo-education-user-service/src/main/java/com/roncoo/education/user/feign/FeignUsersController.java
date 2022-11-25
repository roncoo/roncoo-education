package com.roncoo.education.user.feign;

import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.user.feign.biz.FeignUsersBiz;
import com.roncoo.education.user.feign.interfaces.IFeignUsers;
import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
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
@RequestMapping("/user/users")
public class FeignUsersController extends BaseController implements IFeignUsers {

    @NotNull
    private final FeignUsersBiz biz;

    @Override
    public UsersVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public Map<Long, UsersVO> listByIds(@RequestBody List<Long> userIdList) {
        return biz.listByIds(userIdList);
    }
}
