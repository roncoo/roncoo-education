package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.dao.impl.mapper.entity.ResourceExample;

import java.util.List;

/**
 * 课程视频信息 服务类
 *
 * @author wujing
 * @date 2022-09-02
 */
public interface ResourceDao {

    /**
     * 保存课程视频信息
     *
     * @param record 课程视频信息
     * @return 影响记录数
     */
    int save(Resource record);

    /**
     * 根据ID删除课程视频信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改课程视频信息
     *
     * @param record 课程视频信息
     * @return 影响记录数
     */
    int updateById(Resource record);

    /**
     * 根据ID获取课程视频信息
     *
     * @param id 主键ID
     * @return 课程视频信息
     */
    Resource getById(Long id);

    /**
     * 课程视频信息--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<Resource> page(int pageCurrent, int pageSize, ResourceExample example);

    /**
     * 课程视频信息--条件列出
     *
     * @param example 查询条件
     * @return 课程视频信息列表
     */
    List<Resource> listByExample(ResourceExample example);

    /**
     * 课程视频信息--条件统计
     *
     * @param example 统计条件
     * @return 课程视频信息数量
     */
    int countByExample(ResourceExample example);

    Resource getByVideoVid(String videoVid);

    List<Resource> listByIds(List<Long> ids);
}
