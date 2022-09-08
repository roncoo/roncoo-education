package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.MD5Util;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.dao.impl.mapper.entity.ResourceExample;
import com.roncoo.education.course.dao.impl.mapper.entity.ResourceExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminResourceEditReq;
import com.roncoo.education.course.service.admin.req.AdminResourcePageReq;
import com.roncoo.education.course.service.admin.req.AdminResourceSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminResourcePageResp;
import com.roncoo.education.course.service.admin.resp.AdminResourceViewResp;
import com.roncoo.education.course.service.admin.resp.AdminVodConfigResp;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

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
    private final IFeignSysConfig feignSysConfig;

    public Result<AdminVodConfigResp> getVodConfig() {
        VodConfig vodConfig = feignSysConfig.getVod();
        AdminVodConfigResp resp = new AdminVodConfigResp();
        resp.setVodPlatform(vodConfig.getVodPlatform());
        resp.setPolyvConfig(getCofigByPolyv(vodConfig));
        return Result.success(resp);
    }

    /**
     * 获取保利威上传参数
     *
     * @param vodConfig
     * @return
     */
    private AdminVodConfigResp.PolyvConfig getCofigByPolyv(VodConfig vodConfig) {
        AdminVodConfigResp.PolyvConfig polyvConfig = new AdminVodConfigResp.PolyvConfig();
        polyvConfig.setUserid(vodConfig.getPolyvUserId());
        polyvConfig.setPtime(System.currentTimeMillis());
        polyvConfig.setSign(MD5Util.md5(vodConfig.getPolyvSecretKey() + polyvConfig.getPtime()));
        polyvConfig.setHash(MD5Util.md5(polyvConfig.getPtime() + vodConfig.getPolyvWriteToken()));
        return polyvConfig;
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
        example.setOrderByClause("sort asc, id desc");
        Page<Resource> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminResourcePageResp> respPage = PageUtil.transform(page, AdminResourcePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 课程视频信息添加
     *
     * @param req 课程视频信息
     * @return 添加结果
     */
    public Result<String> save(AdminResourceSaveReq req) {
        Resource record = BeanUtil.copyProperties(req, Resource.class);
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
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
}
