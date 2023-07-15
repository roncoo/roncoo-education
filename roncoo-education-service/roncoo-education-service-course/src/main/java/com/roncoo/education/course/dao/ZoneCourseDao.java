package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourseExample;

import java.util.List;

/**
 * 专区课程关联表 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface ZoneCourseDao {

    /**
     * 保存专区课程关联表
     *
     * @param record 专区课程关联表
     * @return 影响记录数
     */
    int save(ZoneCourse record);

    /**
     * 根据ID删除专区课程关联表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改专区课程关联表
     *
     * @param record 专区课程关联表
     * @return 影响记录数
     */
    int updateById(ZoneCourse record);

    /**
     * 根据ID获取专区课程关联表
     *
     * @param id 主键ID
     * @return 专区课程关联表
     */
    ZoneCourse getById(Long id);

    /**
     * 专区课程关联表--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<ZoneCourse> page(int pageCurrent, int pageSize, ZoneCourseExample example);

    /**
     * 专区课程关联表--条件列出
     *
     * @param example 查询条件
     * @return 专区课程关联表列表
     */
    List<ZoneCourse> listByExample(ZoneCourseExample example);

    /**
     * 专区课程关联表--条件统计
     *
     * @param example 统计条件
     * @return 专区课程关联表数量
     */
    int countByExample(ZoneCourseExample example);
}
