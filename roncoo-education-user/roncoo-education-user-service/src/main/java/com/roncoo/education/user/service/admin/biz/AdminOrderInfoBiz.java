package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoEditReq;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoPageReq;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoPageResp;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoViewResp;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-订单信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminOrderInfoBiz extends BaseBiz {

    @NotNull
    private final OrderInfoDao dao;

    /**
     * 订单信息表分页
     *
     * @param req 订单信息表分页查询参数
     * @return 订单信息表分页查询结果
     */
    public Result<Page<AdminOrderInfoPageResp>> page(AdminOrderInfoPageReq req) {
        OrderInfoExample example = new OrderInfoExample();
        Criteria c = example.createCriteria();
        Page<OrderInfo> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminOrderInfoPageResp> respPage = PageUtil.transform(page, AdminOrderInfoPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 订单信息表添加
     *
     * @param req 订单信息表
     * @return 添加结果
     */
    public Result<String> save(AdminOrderInfoSaveReq req) {
        OrderInfo record = BeanUtil.copyProperties(req, OrderInfo.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 订单信息表查看
     *
     * @param id 主键ID
     * @return 订单信息表
     */
    public Result<AdminOrderInfoViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminOrderInfoViewResp.class));
    }

    /**
     * 订单信息表修改
     *
     * @param req 订单信息表修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminOrderInfoEditReq req) {
        OrderInfo record = BeanUtil.copyProperties(req, OrderInfo.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 订单信息表删除
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
