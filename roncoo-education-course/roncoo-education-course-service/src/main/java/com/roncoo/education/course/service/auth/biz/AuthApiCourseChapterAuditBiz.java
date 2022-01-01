package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.AuditStatusEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.ArrayListUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.course.dao.CourseAuditDao;
import com.roncoo.education.course.dao.CourseChapterAuditDao;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.service.auth.bo.AuthCourseChapterAuditViewBO;
import com.roncoo.education.course.service.auth.bo.*;
import com.roncoo.education.course.service.auth.dto.*;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.IFeignUserExt;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import com.roncoo.education.user.feign.interfaces.vo.UserExtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@Component
public class AuthApiCourseChapterAuditBiz extends BaseBiz {

    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private CourseChapterAuditDao chapterAuditDao;
    @Autowired
    private CourseChapterPeriodAuditDao periodAuditDao;
    @Autowired
    private CourseChapterDao chapterDao;

    @Autowired
    private IFeignUserExt bossUserExt;
    @Autowired
    private IFeignLecturer bossLecturer;

    /**
     * 章节查看接口
     *
     * @param id
     * @return
     * @author wuyun
     */
    public Result<AuthCourseChapterAuditViewDTO> view(AuthCourseChapterAuditViewBO authCourseChapterAuditViewBO) {

        if (StringUtils.isEmpty(authCourseChapterAuditViewBO.getId())) {
            return Result.error("章节id不能为空");
        }

        // 查询章节审核信息
        CourseChapterAudit chapterAudit = chapterAuditDao.getById(authCourseChapterAuditViewBO.getId());
        if (StringUtils.isEmpty(chapterAudit)) {
            return Result.error("找不到章节信息");
        }
        AuthCourseChapterAuditViewDTO dto = BeanUtil.copyProperties(chapterAudit, AuthCourseChapterAuditViewDTO.class);

        // 查询课时审核信息
        List<CourseChapterPeriodAudit> periodAuditList = periodAuditDao.listByChapterIdAndStatusId(authCourseChapterAuditViewBO.getId(), StatusIdEnum.YES.getCode());
        // 写入课时信息集合
        if (CollectionUtil.isNotEmpty(periodAuditList)) {
            List<AuthPeriodAuditViewDTO> periodAuditDTOList = ArrayListUtil.copy(periodAuditList, AuthPeriodAuditViewDTO.class);
            dto.setAuthPeriodAuditView(periodAuditDTOList);
        }
        return Result.success(dto);
    }

    /**
     * 章节删除接口
     *
     * @param id
     * @return
     * @author wuyun
     */
    @Transactional
    public Result<Integer> delete(AuthCourseChapterAuditDeleteBO authCourseChapterAuditDeleteBO) {
        if (authCourseChapterAuditDeleteBO.getId() == null) {
            return Result.error("章节id不能为空");
        }
        if (authCourseChapterAuditDeleteBO.getUserNo() == null) {
            return Result.error("用户编号不能为空");
        }

        // 查询更新后的章节信息
        CourseChapterAudit chapterAudit = chapterAuditDao.getById(authCourseChapterAuditDeleteBO.getId());
        if (ObjectUtils.isEmpty(chapterAudit)) {
            return Result.error("找不到章节信息");
        }
        CourseAudit course = courseAuditDao.getById(chapterAudit.getCourseId());
        if (ObjectUtil.isNull(course)) {
            return Result.error("找不到课程信息");
        }
        if (!authCourseChapterAuditDeleteBO.getUserNo().equals(course.getLecturerUserNo())) {
            return Result.error("传入的useNo与该课程的讲师useNo不一致");
        }

        // 查询章节下是否存在课时信息
        List<CourseChapterPeriodAudit> periodAuditList = periodAuditDao.listByChapterIdAndStatusId(chapterAudit.getId(), StatusIdEnum.YES.getCode());
        if (CollectionUtil.isNotEmpty(periodAuditList)) {
            return Result.error("请先删除章节下的所有课时信息");
        }

        // 将状态改为冻结状态
        CourseChapterAudit record = new CourseChapterAudit();
        record.setId(authCourseChapterAuditDeleteBO.getId());
        record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        record.setStatusId(Constants.FREEZE);
        int result = chapterAuditDao.updateById(record);
        if (result > 0) {
            return Result.success(result);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * 章节列出接口
     *
     * @param courseId
     * @return
     * @author wuyun
     */
    public Result<AuthCourseChapterAuditListDTO> listByCourseNo(AuthCourseChapterAuditBO authCourseChapterAuditBO) {

        if (StringUtils.isEmpty(authCourseChapterAuditBO.getCourseId())) {
            return Result.error("课程ID不能为空");
        }

        AuthCourseChapterAuditListDTO dto = new AuthCourseChapterAuditListDTO();

        // 根据课程ID查询出章节审核信息
        List<CourseChapterAudit> chapterAuditList = chapterAuditDao.listByCourseIdAndStatusId(authCourseChapterAuditBO.getCourseId(), StatusIdEnum.YES.getCode());
        if (CollectionUtil.isNotEmpty(chapterAuditList)) {
            List<AuthCourseChapterAuditDTO> chapterAuditDTOList = ArrayListUtil.copy(chapterAuditList, AuthCourseChapterAuditDTO.class);

            // 根据章节ID查询出课时审核信息
            for (AuthCourseChapterAuditDTO authCourseChapterAuditDTO : chapterAuditDTOList) {
                // 根据章节编号设置课时数量
                List<CourseChapterPeriodAudit> periodAuditList = periodAuditDao.listByChapterIdAndStatusId(authCourseChapterAuditDTO.getId(), StatusIdEnum.YES.getCode());
                authCourseChapterAuditDTO.setPeriodNum(periodAuditList.size());
            }
            dto.setUserChapterAuditList(chapterAuditDTOList);
        }
        return Result.success(dto);
    }

    /**
     * 章节添加接口
     *
     * @param bo
     * @return
     * @author wuyun
     */
    @Transactional
    public Result<AuthCourseChapterAuditSaveDTO> save(AuthCourseChapterAuditSaveBO authCourseChapterAuditSaveBO) {
        if (authCourseChapterAuditSaveBO.getCourseId() == null) {
            return Result.error("courseId不能为空");
        }
        if (StringUtils.isEmpty(authCourseChapterAuditSaveBO.getChapterName())) {
            return Result.error("chapterName不能为空");
        }
        if (authCourseChapterAuditSaveBO.getIsFree() == null) {
            return Result.error("isFree不能为空");
        }
        if (authCourseChapterAuditSaveBO.getUserNo() == null) {
            return Result.error("userNo不能为空");
        }

        // 根据courseNo查找课程信息
        CourseAudit courseAudit = courseAuditDao.getById(authCourseChapterAuditSaveBO.getCourseId());
        if (ObjectUtil.isNull(courseAudit)) {
            return Result.error("找不到该课程信息");
        }
        if (!authCourseChapterAuditSaveBO.getUserNo().equals(courseAudit.getLecturerUserNo())) {
            return Result.error("传入的useNo与该课程的讲师useNo不一致");
        }

        CourseChapterAudit chapterAudit = BeanUtil.copyProperties(authCourseChapterAuditSaveBO, CourseChapterAudit.class);
        chapterAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());

        // 修改成功将课程改为审核状态，查询已修改的章节审核信息回来
        if (chapterAuditDao.save(chapterAudit) > 0) {
            CourseAudit record = new CourseAudit();
            record.setId(chapterAudit.getCourseId());
            record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
            courseAuditDao.updateById(record);

            AuthCourseChapterAuditSaveDTO dto = BeanUtil.copyProperties(chapterAudit, AuthCourseChapterAuditSaveDTO.class);
            return Result.success(dto);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * 章节更新接口
     *
     * @param bo
     * @return
     * @author wuyun
     */
    @Transactional
    public Result<AuthCourseChapterAuditUpdateDTO> update(AuthCourseChapterAuditUpdateBO authCourseChapterAuditUpdateBO) {
        if (StringUtils.isEmpty(authCourseChapterAuditUpdateBO.getId())) {
            return Result.error("章节ID不能为空");
        }
        if (StringUtils.isEmpty(authCourseChapterAuditUpdateBO.getChapterName())) {
            return Result.error("chapterName不能为空");
        }
        if (authCourseChapterAuditUpdateBO.getIsFree() == null) {
            return Result.error("isFree不能为空");
        }
        if (authCourseChapterAuditUpdateBO.getUserNo() == null) {
            return Result.error("userNo不能为空");
        }

        // 根据id查找章节信息
        CourseChapterAudit chapterInfoAudit = chapterAuditDao.getById(authCourseChapterAuditUpdateBO.getId());
        if (ObjectUtil.isNull(chapterInfoAudit)) {
            return Result.error("找不到章节信息");
        }
        CourseAudit course = courseAuditDao.getById(chapterInfoAudit.getCourseId());
        if (ObjectUtil.isNull(course)) {
            return Result.error("找不到该课程信息");
        }
        if (!authCourseChapterAuditUpdateBO.getUserNo().equals(course.getLecturerUserNo())) {
            return Result.error("传入的useNo与该课程的讲师useNo不一致");
        }

        // 设置章节信息审核表为待审核状态
        CourseChapterAudit chapterInfo = BeanUtil.copyProperties(authCourseChapterAuditUpdateBO, CourseChapterAudit.class);
        chapterInfo.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        int result = chapterAuditDao.updateById(chapterInfo);
        if (result > 0) {
            // 修改课程审核表为待审核状态
            CourseAudit courseAudit = new CourseAudit();
            courseAudit.setId(chapterInfoAudit.getCourseId());
            courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
            courseAuditDao.updateById(courseAudit);
            AuthCourseChapterAuditUpdateDTO dto = BeanUtil.copyProperties(chapterInfoAudit, AuthCourseChapterAuditUpdateDTO.class);
            return Result.success(dto);
        }
        return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
    }

    /**
     * 更新章节排序接口
     *
     * @param bo
     * @return
     * @author wuyun
     */
    @Transactional
    public Result<Integer> sort(AuthCourseChapterAuditSortBO authCourseChapterAuditSortBO) {
        if (CollectionUtil.isEmpty(authCourseChapterAuditSortBO.getChapterIdList())) {
            return Result.error("排序失败，请刷新重试");
        }
        if (authCourseChapterAuditSortBO.getUserNo() == null) {
            return Result.error("userNo不能为空");
        }

        UserExtVO userExtVO = bossUserExt.getByUserNo(authCourseChapterAuditSortBO.getUserNo());
        if (ObjectUtils.isEmpty(userExtVO)) {
            return Result.error("找不到用户信息");
        }
        LecturerVO vo = bossLecturer.getByLecturerUserNo(userExtVO.getUserNo());
        if (ObjectUtils.isEmpty(vo)) {
            return Result.error("请先申请成为讲师");
        }
        // 章节ID集合不为空就遍历拿到ID
        if (CollectionUtil.isNotEmpty(authCourseChapterAuditSortBO.getChapterIdList())) {
            int i = 1;
            for (Long chapterId : authCourseChapterAuditSortBO.getChapterIdList()) {
                // 更新章节审核表排序
                chapterAuditDao.updateSortByChapterId(i++, chapterId);
                // 查询更新后的章节审核信息
                CourseChapterAudit chapterAudit = chapterAuditDao.getById(chapterId);
                CourseAudit course = courseAuditDao.getById(chapterAudit.getCourseId());
                if (ObjectUtil.isNull(course)) {
                    return Result.error("找不到该课程信息");
                }
                if (!authCourseChapterAuditSortBO.getUserNo().equals(course.getLecturerUserNo())) {
                    return Result.error("传入的useNo与该课程的讲师useNo不一致");
                }
                // 更新章节信息的排序
                CourseChapter chapter = chapterDao.getById(chapterId);
                if (ObjectUtil.isNotNull(chapter)) {
                    CourseChapter chapterInfo = new CourseChapter();
                    chapterInfo.setId(chapter.getId());
                    chapterInfo.setSort(chapterAudit.getSort());
                    chapterDao.updateById(chapterInfo);
                }
            }
            return Result.success(i);
        }
        return Result.error("更新排序失败");
    }
}
