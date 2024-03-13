package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResourceTypeEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.VodUtil;
import com.roncoo.education.common.video.req.VodPlayConfigReq;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.dao.impl.mapper.entity.ResourceExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminResourceDeleteReq;
import com.roncoo.education.course.service.admin.req.AdminResourceEditReq;
import com.roncoo.education.course.service.admin.req.AdminResourcePageReq;
import com.roncoo.education.course.service.admin.req.AdminResourceSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminPreviewResp;
import com.roncoo.education.course.service.admin.resp.AdminResourcePageResp;
import com.roncoo.education.course.service.admin.resp.AdminResourceViewResp;
import com.roncoo.education.course.service.admin.resp.AdminVodConfigResp;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ADMIN-课程视频信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminResourceBiz extends BaseBiz {

    @NotNull
    private final ResourceDao dao;

    @NotNull
    private final CategoryDao categoryDao;
    @NotNull
    private final CourseChapterPeriodDao courseChapterPeriodDao;

    @NotNull
    private final IFeignSysConfig feignSysConfig;

    @Autowired
    private HttpServletRequest request;

    public Result<AdminVodConfigResp> getVodConfig() {
        AdminVodConfigResp resp = new AdminVodConfigResp();
        // 视频云配置
        VodConfig vodConfig = feignSysConfig.getVod();
        resp.setVodPlatform(vodConfig.getVodPlatform());
        resp.setVodUploadConfig(VodUtil.getUploadConfig(vodConfig));
        return Result.success(resp);
    }

    /**
     * 课程视频信息分页
     *
     * @param req 课程视频信息分页查询参数
     * @return 课程视频信息分页查询结果
     */
    public Result<Page<AdminResourcePageResp>> page(AdminResourcePageReq req) {
        ResourceExample example = new ResourceExample();
        Criteria c = example.createCriteria();
        if (ObjectUtil.isNotNull(req.getResourceType())) {
            c.andResourceTypeEqualTo(req.getResourceType());
        }
        if (StringUtils.hasText(req.getResourceName())) {
            c.andResourceNameLike(PageUtil.rightLike(req.getResourceName()));
        }
        if (ObjectUtil.isNotNull(req.getCategoryId())) {
            List<Category> list = categoryDao.listByExample(new CategoryExample());
            List<Long> ids = new ArrayList<>();
            ids.add(req.getCategoryId());
            filter(ids, list, req.getCategoryId());
            c.andCategoryIdIn(ids);
        }

        example.setOrderByClause("sort asc, id desc");
        Page<Resource> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminResourcePageResp> respPage = PageUtil.transform(page, AdminResourcePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 层级处理
     */
    private void filter(List<Long> ids, List<Category> categoryList, Long categoryId) {
        for (Category category : categoryList) {
            if (categoryId.compareTo(category.getParentId()) == 0) {
                ids.add(category.getId());
                filter(ids, categoryList, category.getId());
            }
        }
    }

    /**
     * 课程视频信息添加
     *
     * @param req 课程视频信息
     * @return 添加结果
     */
    public Result<String> save(AdminResourceSaveReq req) {
        Resource record = BeanUtil.copyProperties(req, Resource.class);
        if (ResourceTypeEnum.VIDEO.getCode().equals(req.getResourceType()) || ResourceTypeEnum.AUDIO.getCode().equals(req.getResourceType())) {
            // 视频类型，填写视频平台
            record.setVodPlatform(feignSysConfig.getVod().getVodPlatform());
        } else {
            // 文档类型，填写存储平台
            record.setStoragePlatform(feignSysConfig.getSys().getStoragePlatform());
        }
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程视频信息查看
     *
     * @param id 主键ID
     * @return 课程视频信息
     */
    public Result<AdminResourceViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminResourceViewResp.class));
    }

    /**
     * 课程视频信息修改
     *
     * @param req 课程视频信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminResourceEditReq req) {
        Resource record = BeanUtil.copyProperties(req, Resource.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程视频信息删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(Long id) {
        Resource resource = dao.getById(id);
        if (ObjectUtil.isNull(resource)) {
            return Result.error("资源不存在");
        }
        return handleDelete(resource);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> batchDelete(AdminResourceDeleteReq req) {
        List<Resource> resources = dao.listByIds(req.getIds());
        if (CollUtil.isEmpty(resources)) {
            return Result.error("资源不存在");
        }
        for (Resource resource : resources) {
            Result<String> result = handleDelete(resource);
            if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
                return result;
            }
        }
        return Result.success("操作成功");
    }

    private Result<String> handleDelete(Resource resource) {
        List<CourseChapterPeriod> record = courseChapterPeriodDao.listByResourceId(resource.getId());
        if (CollUtil.isNotEmpty(record)) {
            log.warn("{}：资源引用={}", JSUtil.toJsonString(resource), JSUtil.toJsonString(record.stream().map(CourseChapterPeriod::getPeriodName).collect(Collectors.toList())));
            return Result.error(resource.getResourceName() + "，该资源存在引用，暂不能删除");
        }
        if (dao.deleteById(resource.getId()) > 0) {
            if (ResourceTypeEnum.VIDEO.getCode().equals(resource.getResourceType()) || ResourceTypeEnum.AUDIO.getCode().equals(resource.getResourceType())) {
                // 删除音视频
                VodUtil.deleteVideo(feignSysConfig.getVod(), resource.getVideoVid());
            } else if (ResourceTypeEnum.DOC.getCode().equals(resource.getResourceType())) {
                // 删除文档
            }
        }
        return Result.success("操作成功");
    }

    public Result<AdminPreviewResp> preview(Long id) {
        Resource resource = dao.getById(id);
        if (ObjectUtil.isNull(resource)) {
            return Result.error("资源不存在");
        }
        AdminPreviewResp resp = new AdminPreviewResp();
        resp.setId(resource.getId());
        resp.setVid(resource.getVideoVid());
        resp.setVodPlatform(resource.getVodPlatform());

        VodPlayConfigReq playConfigReq = new VodPlayConfigReq();
        playConfigReq.setVid(resp.getVid());
        playConfigReq.setViewerId(ThreadContext.userId().toString());
        playConfigReq.setViewerIp(ServletUtil.getClientIP(request));
        VodPlayConfigReq.VodAuthCode authCode = new VodPlayConfigReq.VodAuthCode();
        authCode.setUserId(ThreadContext.userId());
        playConfigReq.setVodAuthCode(authCode);

        VodConfig vodConfig = feignSysConfig.getVod();
        vodConfig.setVodPlatform(resp.getVodPlatform());
        resp.setVodPlayConfig(VodUtil.getPlayConfig(vodConfig, playConfigReq));

        return Result.success(resp);
    }


}
