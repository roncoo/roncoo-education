package com.roncoo.education.user.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.qo.RegionPageQO;
import com.roncoo.education.user.feign.interfaces.qo.RegionSaveQO;
import com.roncoo.education.user.feign.interfaces.qo.RegionEditQO;
import com.roncoo.education.user.feign.interfaces.vo.RegionPageVO;
import com.roncoo.education.user.feign.interfaces.vo.RegionViewVO;

/**
 * 行政区域表 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "user-service", path = "/user/region")
public interface IFeignRegion {

    /**
     * 分页列出-行政区域表
     *
     * @param qo 行政区域表
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<RegionPageVO> page(@RequestBody RegionPageQO qo);

    /**
     * 保存-行政区域表
     *
     * @param qo 行政区域表
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody RegionSaveQO qo);

    /**
     * 根据ID删除-行政区域表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改行政区域表
     *
     * @param qo 行政区域表
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody RegionEditQO qo);

    /**
     * 根据ID获取行政区域表
     *
     * @param id 主键ID
     * @return 行政区域表
     */
    @GetMapping(value = "/get/{id}")
    RegionViewVO getById(@PathVariable(value = "id") Long id);
}
