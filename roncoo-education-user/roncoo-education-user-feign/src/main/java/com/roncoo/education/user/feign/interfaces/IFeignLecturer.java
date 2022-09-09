package com.roncoo.education.user.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.LecturerEditQO;
import com.roncoo.education.user.feign.interfaces.qo.LecturerPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LecturerSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerViewVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 讲师信息 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "user-service", path = "/user/lecturer")
public interface IFeignLecturer {

    /**
     * 分页列出-讲师信息
     *
     * @param qo 讲师信息
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<LecturerPageVO> page(@RequestBody LecturerPageQO qo);

    /**
     * 保存-讲师信息
     *
     * @param qo 讲师信息
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody LecturerSaveQO qo);

    /**
     * 根据ID删除-讲师信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改讲师信息
     *
     * @param qo 讲师信息
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody LecturerEditQO qo);

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
