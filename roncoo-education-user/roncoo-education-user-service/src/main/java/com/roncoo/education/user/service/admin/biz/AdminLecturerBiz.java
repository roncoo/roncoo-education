package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminLecturerEditReq;
import com.roncoo.education.user.service.admin.req.AdminLecturerPageReq;
import com.roncoo.education.user.service.admin.req.AdminLecturerSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminLecturerPageResp;
import com.roncoo.education.user.service.admin.resp.AdminLecturerViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-讲师信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminLecturerBiz extends BaseBiz {

    @NotNull
    private final LecturerDao dao;

    /**
     * 讲师信息分页
     *
     * @param req 讲师信息分页查询参数
     * @return 讲师信息分页查询结果
     */
    public Result<Page<AdminLecturerPageResp>> page(AdminLecturerPageReq req) {
        LecturerExample example = new LecturerExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getLecturerName())) {
            c.andLecturerNameLike(PageUtil.rightLike(req.getLecturerName()));
        }
        example.setOrderByClause("sort asc, id desc");
        Page<Lecturer> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminLecturerPageResp> respPage = PageUtil.transform(page, AdminLecturerPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 讲师信息添加
     *
     * @param req 讲师信息
     * @return 添加结果
     */
    public Result<String> save(AdminLecturerSaveReq req) {
        Lecturer record = BeanUtil.copyProperties(req, Lecturer.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 讲师信息查看
     *
     * @param id 主键ID
     * @return 讲师信息
     */
    public Result<AdminLecturerViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminLecturerViewResp.class));
    }

    /**
     * 讲师信息修改
     *
     * @param req 讲师信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminLecturerEditReq req) {
        Lecturer record = BeanUtil.copyProperties(req, Lecturer.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 讲师信息删除
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
