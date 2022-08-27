package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.UserStudyPageQO;
import com.roncoo.education.course.feign.interfaces.qo.UserStudySaveQO;
import com.roncoo.education.course.feign.interfaces.qo.UserStudyEditQO;
import com.roncoo.education.course.feign.interfaces.vo.UserStudyPageVO;
import com.roncoo.education.course.feign.interfaces.vo.UserStudyViewVO;

/**
 * 课程用户学习日志 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "course-service", path = "/course/user/study")
public interface IFeignUserStudy {

    /**
     * 分页列出-课程用户学习日志
     *
     * @param qo 课程用户学习日志
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<UserStudyPageVO> page(@RequestBody UserStudyPageQO qo);

    /**
     * 保存-课程用户学习日志
     *
     * @param qo 课程用户学习日志
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody UserStudySaveQO qo);

    /**
     * 根据ID删除-课程用户学习日志
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改课程用户学习日志
     *
     * @param qo 课程用户学习日志
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody UserStudyEditQO qo);

    /**
     * 根据ID获取课程用户学习日志
     *
     * @param id 主键ID
     * @return 课程用户学习日志
     */
    @GetMapping(value = "/get/{id}")
    UserStudyViewVO getById(@PathVariable(value = "id") Long id);

}
