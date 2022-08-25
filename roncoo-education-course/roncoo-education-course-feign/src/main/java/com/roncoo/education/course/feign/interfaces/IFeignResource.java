package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.ResourcePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ResourceSaveQO;
import com.roncoo.education.course.feign.interfaces.qo.ResourceEditQO;
import com.roncoo.education.course.feign.interfaces.vo.ResourcePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ResourceViewVO;

/**
 * 课程视频信息 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "course-service", path = "/course/resource")
public interface IFeignResource {

    /**
     * 分页列出-课程视频信息
     *
     * @param qo 课程视频信息
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<ResourcePageVO> page(@RequestBody ResourcePageQO qo);

    /**
     * 保存-课程视频信息
     *
     * @param qo 课程视频信息
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody ResourceSaveQO qo);

    /**
     * 根据ID删除-课程视频信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改课程视频信息
     *
     * @param qo 课程视频信息
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody ResourceEditQO qo);

    /**
     * 根据ID获取课程视频信息
     *
     * @param id 主键ID
     * @return 课程视频信息
     */
    @GetMapping(value = "/get/{id}")
    ResourceViewVO getById(@PathVariable(value = "id") Long id);

}
