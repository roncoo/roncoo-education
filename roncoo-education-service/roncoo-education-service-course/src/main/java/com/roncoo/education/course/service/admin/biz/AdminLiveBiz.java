package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.service.admin.req.AdminLiveEditReq;
import com.roncoo.education.course.service.admin.req.AdminLivePageReq;
import com.roncoo.education.course.service.admin.req.AdminLiveSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminLivePageResp;
import com.roncoo.education.course.service.admin.resp.AdminLiveViewResp;
import com.roncoo.education.course.dao.LiveDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Live;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveExample;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveExample.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-直播信息
 *
 * @author fengyw
 */
@Component
@RequiredArgsConstructor
public class AdminLiveBiz extends BaseBiz {

    @NotNull
    private final LiveDao dao;

    /**
     * 直播信息分页
     *
     * @param req 直播信息分页查询参数
     * @return 直播信息分页查询结果
     */
    public Result<Page<AdminLivePageResp>> page(AdminLivePageReq req) {
        LiveExample example = new LiveExample();
        Criteria c = example.createCriteria();
        Page<Live> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminLivePageResp> respPage = PageUtil.transform(page, AdminLivePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 直播信息添加
     *
     * @param req 直播信息
     * @return 添加结果
     */
    public Result<String> save(AdminLiveSaveReq req) {
        Live record = BeanUtil.copyProperties(req, Live.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 直播信息查看
     *
     * @param id 主键ID
     * @return 直播信息
     */
    public Result<AdminLiveViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminLiveViewResp.class));
    }

    /**
     * 直播信息修改
     *
     * @param req 直播信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminLiveEditReq req) {
        Live record = BeanUtil.copyProperties(req, Live.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 直播信息删除
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
