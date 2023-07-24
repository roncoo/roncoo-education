package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.user.feign.interfaces.vo.LecturerViewVO;
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
@FeignClient(value = "service-user", path = "/user/lecturer")
public interface IFeignLecturer {
    /**
     * 根据ID获取讲师信息
     *
     * @param id 主键ID
     * @return 讲师信息
     */
    @GetMapping(value = "/get/{id}")
    LecturerViewVO getById(@PathVariable(value = "id") Long id);

    /**
     * 根据ID集合获取讲师名称集合
     *
     * @param lecturerIdList
     * @return
     */
    @PostMapping(value = "/listByIds")
    Map<Long, String> listByIds(@RequestBody List<Long> lecturerIdList);
}
