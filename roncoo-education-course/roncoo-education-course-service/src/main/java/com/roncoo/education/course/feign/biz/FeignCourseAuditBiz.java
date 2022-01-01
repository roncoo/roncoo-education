package com.roncoo.education.course.feign.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.aliyun.AliyunUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.AuditStatusEnum;
import com.roncoo.education.common.core.enums.IsDocEnum;
import com.roncoo.education.common.core.enums.IsFreeEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.es.EsCourse;
import com.roncoo.education.course.dao.*;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseAuditExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.CourseAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseAuditVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterAuditVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodAuditVO;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@Component
public class FeignCourseAuditBiz extends BaseBiz {

    @Autowired
    private CourseAuditDao dao;
    @Autowired
    private CourseCategoryDao courseCategoryDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseChapterAuditDao courseChapterAuditDao;
    @Autowired
    private CourseChapterDao courseChapterDao;
    @Autowired
    private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
    @Autowired
    private CourseChapterPeriodDao courseChapterPeriodDao;
    @Autowired
    private CourseIntroduceDao courseIntroduceDao;
    @Autowired
    private CourseIntroduceAuditDao courseIntroduceAuditDao;

    @Autowired
    private IFeignLecturer bossLecturer;
    @Autowired
    private IFeignSys bossSys;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Page<CourseAuditVO> listForPage(CourseAuditQO qo) {
        CourseAuditExample example = new CourseAuditExample();
        Criteria c = example.createCriteria();

        if (!StringUtils.isEmpty(qo.getCourseName())) {
            c.andCourseNameLike(PageUtil.rightLike(qo.getCourseName()));
        }
        if (qo.getStatusId() != null) {
            c.andStatusIdEqualTo(qo.getStatusId());
        }
        if (qo.getAuditStatus() == null) {
            c.andAuditStatusNotEqualTo(AuditStatusEnum.SUCCESS.getCode());
        } else {
            c.andAuditStatusEqualTo(qo.getAuditStatus());
        }
        if (qo.getIsFree() != null) {
            c.andIsFreeEqualTo(qo.getIsFree());
        }
        if (qo.getIsPutaway() != null) {
            c.andIsPutawayEqualTo(qo.getIsPutaway());
        }

        example.setOrderByClause(" status_id desc, is_putaway desc, sort desc, id desc ");
        Page<CourseAudit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        Page<CourseAuditVO> CourseAuditVOList = PageUtil.transform(page, CourseAuditVO.class);
        // 获取分类名称
        for (CourseAuditVO courseAuditVO : CourseAuditVOList.getList()) {
            if (courseAuditVO.getCategoryId1() != null && courseAuditVO.getCategoryId1() != 0) {
                CourseCategory courseCategory = courseCategoryDao.getById(courseAuditVO.getCategoryId1());
                if (!StringUtils.isEmpty(courseCategory)) {
                    courseAuditVO.setCategoryName1(courseCategory.getCategoryName());
                }
            }
            if (courseAuditVO.getCategoryId2() != null && courseAuditVO.getCategoryId2() != 0) {
                CourseCategory courseCategory = courseCategoryDao.getById(courseAuditVO.getCategoryId2());
                if (!StringUtils.isEmpty(courseCategory)) {
                    courseAuditVO.setCategoryName2(courseCategory.getCategoryName());
                }
            }
            if (courseAuditVO.getCategoryId3() != null && courseAuditVO.getCategoryId3() != 0) {
                CourseCategory courseCategory = courseCategoryDao.getById(courseAuditVO.getCategoryId3());
                if (!StringUtils.isEmpty(courseCategory)) {
                    courseAuditVO.setCategoryName3(courseCategory.getCategoryName());
                }
            }
        }
        return CourseAuditVOList;
    }

    public int save(CourseAuditQO qo) {
        CourseAudit record = BeanUtil.copyProperties(qo, CourseAudit.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseAuditVO getById(Long id) {
        CourseAudit record = dao.getById(id);
        CourseAuditVO vo = BeanUtil.copyProperties(record, CourseAuditVO.class);
        // 获取分类名称
        if (vo.getCategoryId1() != null && vo.getCategoryId1() != 0) {
            CourseCategory courseCategory = courseCategoryDao.getById(vo.getCategoryId1());
            vo.setCategoryName1(courseCategory.getCategoryName());
        }
        if (vo.getCategoryId2() != null && vo.getCategoryId2() != 0) {
            CourseCategory courseCategory = courseCategoryDao.getById(vo.getCategoryId2());
            vo.setCategoryName2(courseCategory.getCategoryName());
        }
        if (vo.getCategoryId3() != null && vo.getCategoryId3() != 0) {
            CourseCategory courseCategory = courseCategoryDao.getById(vo.getCategoryId3());
            vo.setCategoryName3(courseCategory.getCategoryName());
        }

        // 课程简介
        CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(vo.getIntroduceId());
        vo.setIntroduceId(courseIntroduceAudit.getId());
        vo.setIntroduce(courseIntroduceAudit.getIntroduce());

        // 查询讲师信息
        LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(vo.getLecturerUserNo());
        if (ObjectUtil.isNull(lecturerVO)) {
            throw new BaseException("找不到讲师信息");
        }
        vo.setLecturerName(lecturerVO.getLecturerName());

        // 章节
        List<CourseChapterAudit> ChapterList = courseChapterAuditDao.listByCourseIdAndStatusId(vo.getId(), StatusIdEnum.YES.getCode());
        if (CollectionUtil.isNotEmpty(ChapterList)) {
            List<CourseChapterAuditVO> courseChapterVOList = new ArrayList<>();
            for (CourseChapterAudit courseChapter : ChapterList) {
                // 课时
                List<CourseChapterPeriodAudit> periodList = courseChapterPeriodAuditDao.listByChapterIdAndStatusId(courseChapter.getId(), StatusIdEnum.YES.getCode());
                CourseChapterAuditVO courseChapterVO = BeanUtil.copyProperties(courseChapter, CourseChapterAuditVO.class);
                courseChapterVO.setCourseChapterPeriodAuditList(PageUtil.copyList(periodList, CourseChapterPeriodAuditVO.class));
                courseChapterVOList.add(courseChapterVO);
            }
            vo.setCourseChapterAuditList(courseChapterVOList);
        }
        return vo;
    }

    @Transactional
    public int updateById(CourseAuditQO qo) {
        if (IsFreeEnum.FREE.getCode().equals(qo.getIsFree())) {
            qo.setCourseOriginal(BigDecimal.ZERO);
            qo.setCourseDiscount(BigDecimal.ZERO);
        }
        CourseAudit record = BeanUtil.copyProperties(qo, CourseAudit.class);
        record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        int result = dao.updateById(record);
        if (result < 1) {
            throw new BaseException("课程信息表更新失败");
        }
        // 查询课程简介
        CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(qo.getIntroduceId());
        if (ObjectUtil.isNull(courseIntroduceAudit)) {
            throw new BaseException("找不到课程简介信息");
        }
        CourseIntroduceAudit introduceAudit = new CourseIntroduceAudit();
        introduceAudit.setId(qo.getIntroduceId());
        introduceAudit.setIntroduce(qo.getIntroduce());
        return courseIntroduceAuditDao.updateById(introduceAudit);
    }

    @Transactional
    public int audit(CourseAuditQO qo) {
        // 不成功
        if (!AuditStatusEnum.SUCCESS.getCode().equals(qo.getAuditStatus())) {
            CourseAudit audit = BeanUtil.copyProperties(qo, CourseAudit.class);
            return dao.updateById(audit);
        }
        // 审核 课程-章节-课时
        CourseAudit courseAudit = dao.getById(qo.getId());
        if (ObjectUtil.isNull(courseAudit)) {
            throw new BaseException("课程不存在");
        }

        Course course = courseDao.getById(courseAudit.getId());

        // 根据课程ID查询课时信息集合
        List<CourseChapterPeriodAudit> periodAuditList = courseChapterPeriodAuditDao.listByCourseId(courseAudit.getId());

        // 1、对课程操作
        // 如果课程信息表里面有数据就进行更新
        if (ObjectUtil.isNotNull(course)) {
            course = BeanUtil.copyProperties(courseAudit, Course.class);
            course.setGmtCreate(null);
            course.setGmtModified(null);
            // 设置总课时数
            if (CollectionUtil.isEmpty(periodAuditList)) {
                course.setPeriodTotal(0);
            } else {
                course.setPeriodTotal(periodAuditList.size());
            }
            // 更新课程信息表
            courseDao.updateById(course);
        } else {
            // 如果课程信息表里面没数据就进行插入
            Course info = BeanUtil.copyProperties(courseAudit, Course.class);
            info.setGmtCreate(null);
            info.setGmtModified(null);
            // 设置总课时数
            if (CollectionUtil.isEmpty(periodAuditList)) {
                info.setPeriodTotal(0);
            } else {
                info.setPeriodTotal(periodAuditList.size());
            }
            courseDao.save(info);
        }

        // 2、对课程简介操作
        CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(courseAudit.getIntroduceId());
        CourseIntroduce courseIntroduce = courseIntroduceDao.getById(courseAudit.getIntroduceId());
        if (ObjectUtil.isNull(courseIntroduceAudit)) {
            throw new BaseException("课程简介信息表不存在");
        }
        if (ObjectUtil.isNull(courseIntroduce)) {
            CourseIntroduce introduce = BeanUtil.copyProperties(courseIntroduceAudit, CourseIntroduce.class);
            courseIntroduceDao.save(introduce);
        } else {
            courseIntroduce = BeanUtil.copyProperties(courseIntroduceAudit, CourseIntroduce.class);
            courseIntroduceAuditDao.updateById(courseIntroduceAudit);
        }

        // 3、对章节操作
        // 根据课程编号查找章节审核信息集合
        List<CourseChapterAudit> courseChapterAuditList = courseChapterAuditDao.listByCourseId(courseAudit.getId());
        if (CollectionUtil.isNotEmpty(courseChapterAuditList)) {
            for (CourseChapterAudit courseChapterAudit : courseChapterAuditList) {
                // 根据章节编号查找章节审核信息
                CourseChapterAudit infoAudit = courseChapterAuditDao.getById(courseChapterAudit.getId());
                // 查找章节信息表是否存在该课时信息
                CourseChapter chapter = courseChapterDao.getById(courseChapterAudit.getId());
                // 存在就更新章节信息表数据
                if (ObjectUtil.isNotNull(chapter)) {
                    chapter = BeanUtil.copyProperties(infoAudit, CourseChapter.class);
                    chapter.setGmtCreate(null);
                    chapter.setGmtModified(null);
                    courseChapterDao.updateById(chapter);
                } else {
                    // 如果章节不存在则插入章节信息
                    chapter = BeanUtil.copyProperties(infoAudit, CourseChapter.class);
                    chapter.setGmtCreate(null);
                    chapter.setGmtModified(null);
                    courseChapterDao.save(chapter);
                }
                // 更新审核状态
                infoAudit.setAuditStatus(AuditStatusEnum.SUCCESS.getCode());
                courseChapterAuditDao.updateById(infoAudit);
            }
        }

        // 4、对课时操作
        // 根据课程编号查找课时审核信息集合
        List<CourseChapterPeriodAudit> courseChapterPeriodAuditList = courseChapterPeriodAuditDao.listByCourseId(courseAudit.getId());
        if (ObjectUtil.isNotNull(courseChapterPeriodAuditList)) {
            for (CourseChapterPeriodAudit courseChapterPeriodAudit : courseChapterPeriodAuditList) {
                // 根据课时编号查找课时审核信息
                CourseChapterPeriodAudit chapterperiodAudit = courseChapterPeriodAuditDao.getById(courseChapterPeriodAudit.getId());
                // 根据课时编号查找课时信息
                CourseChapterPeriod chapterPeriod = courseChapterPeriodDao.getById(courseChapterPeriodAudit.getId());
                // 如果信息表存在就更新信息表信息
                if (ObjectUtil.isNotNull(chapterPeriod)) {
                    if (IsDocEnum.NO.getCode().equals(chapterPeriod.getIsDoc())) {
                        AliyunUtil.delete(chapterPeriod.getDocUrl(), BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class));
                    }
                    chapterPeriod = BeanUtil.copyProperties(chapterperiodAudit, CourseChapterPeriod.class);
                    chapterPeriod.setGmtCreate(null);
                    chapterPeriod.setGmtModified(null);
                    courseChapterPeriodDao.updateById(chapterPeriod);
                } else {
                    // 如果课时信息表不存在就插入信息
                    chapterPeriod = BeanUtil.copyProperties(chapterperiodAudit, CourseChapterPeriod.class);
                    chapterPeriod.setGmtCreate(null);
                    chapterPeriod.setGmtModified(null);
                    courseChapterPeriodDao.save(chapterPeriod);
                }
                // 更新审核状态
                chapterperiodAudit.setAuditStatus(AuditStatusEnum.SUCCESS.getCode());
                courseChapterPeriodAuditDao.updateById(chapterperiodAudit);
            }
        }

        // 更改课程审核状态
        CourseAudit audit = BeanUtil.copyProperties(qo, CourseAudit.class);
        int resultNum = dao.updateById(audit);
        if (resultNum > 0) {
            try {
                // 查询讲师名称并插入es
                LecturerVO lecturerInfoVO = bossLecturer.getByLecturerUserNo(courseAudit.getLecturerUserNo());
                // 插入es或者更新es
                EsCourse esCourse = BeanUtil.copyProperties(courseAudit, EsCourse.class);
                if (!ObjectUtils.isEmpty(lecturerInfoVO) && !StringUtils.isEmpty(lecturerInfoVO.getLecturerName()) && lecturerInfoVO.getStatusId().equals(StatusIdEnum.YES.getCode())) {
                    esCourse.setLecturerName(lecturerInfoVO.getLecturerName());
                }
                IndexQuery query = new IndexQueryBuilder().withObject(esCourse).build();
                elasticsearchRestTemplate.index(query, IndexCoordinates.of(EsCourse.COURSE));
            } catch (Exception e) {
                logger.error("elasticsearch更新数据失败", e);
            }
        }

        return resultNum;
    }

    /**
     * 更新状态信息
     *
     * @param qo
     * @return
     * @author wuyun
     */
    public int updateStatusId(CourseAuditQO qo) {
        CourseAudit audit = BeanUtil.copyProperties(qo, CourseAudit.class);
        return dao.updateById(audit);
    }
}
