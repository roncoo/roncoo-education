package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 讲师信息 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "service-user", path = "/user/users")
public interface IFeignUsers {
    /**
     * 根据ID获取信息
     *
     * @param id 主键ID
     * @return 讲师信息
     */
    @GetMapping(value = "/get/{id}")
    UsersVO getById(@PathVariable(value = "id") Long id);

    /**
     * 根据ID集合获取集合
     *
     * @param userIdList
     * @return
     */
    @PostMapping(value = "/listByIds")
    Map<Long, UsersVO> listByIds(@RequestBody List<Long> userIdList);
}
