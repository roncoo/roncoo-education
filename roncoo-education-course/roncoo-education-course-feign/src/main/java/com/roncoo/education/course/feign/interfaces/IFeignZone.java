package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.ZonePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneSaveQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneEditQO;
import com.roncoo.education.course.feign.interfaces.vo.ZonePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneViewVO;

/**
 * 专区 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "course-service", path = "/course/zone")
public interface IFeignZone {

    /**
     * 分页列出-专区
     *
     * @param qo 专区
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<ZonePageVO> page(@RequestBody ZonePageQO qo);

    /**
     * 保存-专区
     *
     * @param qo 专区
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody ZoneSaveQO qo);

    /**
     * 根据ID删除-专区
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改专区
     *
     * @param qo 专区
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody ZoneEditQO qo);

    /**
     * 根据ID获取专区
     *
     * @param id 主键ID
     * @return 专区
     */
    @GetMapping(value = "/get/{id}")
    ZoneViewVO getById(@PathVariable(value = "id") Long id);

}
