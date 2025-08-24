package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.PeriodTypeEnum;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.common.base.SortReq;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.LiveDao;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample.Criteria;
import com.roncoo.education.course.dao.impl.mapper.entity.Live;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodEditReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodListReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodPageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPeriodPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPeriodViewResp;
import com.roncoo.education.course.service.admin.resp.AdminLiveViewResp;
import com.roncoo.education.course.service.admin.resp.AdminResourceViewResp;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-课时信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminCourseChapterPeriodBiz extends BaseBiz {

    @NotNull
    private final CourseChapterPeriodDao dao;
    @NotNull
    private final LiveDao liveDao;

    @NotNull
    private final UserStudyDao userStudyDao;
    @NotNull
    private final ResourceDao resourceDao;
    @NotNull
    private final IFeignLecturer feignLecturer;

    /**
     * 课时信息分页
     *
     * @param req 课时信息分页查询参数
     * @return 课时信息分页查询结果
     */
    public Result<Page<AdminCourseChapterPeriodPageResp>> page(AdminCourseChapterPeriodPageReq req) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        Criteria c = example.createCriteria();
        Page<CourseChapterPeriod> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminCourseChapterPeriodPageResp> respPage = PageUtil.transform(page, AdminCourseChapterPeriodPageResp.class);
        return Result.success(respPage);
    }

    public Result<List<AdminCourseChapterPeriodViewResp>> list(AdminCourseChapterPeriodListReq req) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        Criteria c = example.createCriteria();
        c.andChapterIdEqualTo(req.getChapterId());
        example.setOrderByClause("sort asc, id desc");
        List<CourseChapterPeriod> list = dao.listByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            List<AdminCourseChapterPeriodViewResp> respList = BeanUtil.copyProperties(list, AdminCourseChapterPeriodViewResp.class);
            // 资源
            List<Long> resourceIdList = list.stream().filter(courseChapterPeriod -> courseChapterPeriod.getPeriodType().equals(PeriodTypeEnum.RESOURCE.getCode())).map(courseChapterPeriod -> courseChapterPeriod.getResourceId()).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(resourceIdList)) {
                Map<Long, Resource> resourceMap = resourceDao.listByIds(resourceIdList).stream().collect(Collectors.toMap(Resource::getId, item -> item));
                for (AdminCourseChapterPeriodViewResp period : respList) {
                    period.setResourceViewResp(BeanUtil.copyProperties(resourceMap.get(period.getResourceId()), AdminResourceViewResp.class));
                }
            }
            // 直播
            List<Long> liveIdList = list.stream().filter(courseChapterPeriod -> courseChapterPeriod.getPeriodType().equals(PeriodTypeEnum.LIVE.getCode())).map(courseChapterPeriod -> courseChapterPeriod.getLiveId()).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(liveIdList)) {
                List<Live> liveList = liveDao.listByIds(liveIdList);
                Map<Long, Live> liveMap = liveList.stream().collect(Collectors.toMap(Live::getId, item -> item));
                // 讲师
                List<Long> lecturerIdList = liveList.stream().map(Live::getLecturerId).collect(Collectors.toList());
                Map<Long, String> lecturerNameMap = feignLecturer.listByIds(lecturerIdList);
                for (AdminCourseChapterPeriodViewResp period : respList) {
                    if (period.getLiveId() > 0) {
                        AdminLiveViewResp liveViewResp = BeanUtil.copyProperties(liveMap.get(period.getLiveId()), AdminLiveViewResp.class);
                        liveViewResp.setLecturerName(lecturerNameMap.get(liveViewResp.getLecturerId()));
                        period.setLiveViewResp(liveViewResp);
                    }
                }
            }
            return Result.success(respList);
        }
        return Result.success(new ArrayList<>());
    }

    /**
     * 课时信息添加
     *
     * @param req 课时信息
     * @return 添加结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(AdminCourseChapterPeriodSaveReq req) {
        int maxSort = 0;
        List<CourseChapterPeriod> periodList = dao.listByChapterId(req.getChapterId());
        if (CollUtil.isNotEmpty(periodList)) {
            List<SortReq> sorts = new ArrayList<>();
            for (CourseChapterPeriod period : periodList) {
                // 重新排序
                sorts.add(new SortReq().setId(period.getId()).setSort(maxSort += 1));
            }
            sort(sorts, "CourseChapterPeriod");
        }

        CourseChapterPeriod record = BeanUtil.copyProperties(req, CourseChapterPeriod.class);
        record.setSort(maxSort + 1);

        if (req.getPeriodType().equals(PeriodTypeEnum.LIVE.getCode())) {
            // 直播
            Live live = BeanUtil.copyProperties(req, Live.class);
            live.setLiveName(req.getPeriodName());
            liveDao.save(live);
            // 保存直播记录
            record.setLiveId(live.getId());
        }

        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课时信息查看
     *
     * @param id 主键ID
     * @return 课时信息
     */
    public Result<AdminCourseChapterPeriodViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminCourseChapterPeriodViewResp.class));
    }

    /**
     * 课时信息修改
     *
     * @param req 课时信息修改对象
     * @return 修改结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<String> edit(AdminCourseChapterPeriodEditReq req) {
        CourseChapterPeriod record = BeanUtil.copyProperties(req, CourseChapterPeriod.class);

        if (req.getPeriodType().equals(PeriodTypeEnum.LIVE.getCode())) {
            // 直播
            Live live = BeanUtil.copyProperties(req, Live.class);
            live.setLiveName(req.getPeriodName());
            live.setId(req.getLiveId());
            liveDao.updateById(live);
        }

        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课时信息删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            // 删除课时，也需要删除对应的学习记录，否则统计进度出现数据异常
            userStudyDao.deleteByPeriodId(id);
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
}
