package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.OrderPayDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPayExample;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPayExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminOrderPayEditReq;
import com.roncoo.education.user.service.admin.req.AdminOrderPayPageReq;
import com.roncoo.education.user.service.admin.req.AdminOrderPaySaveReq;
import com.roncoo.education.user.service.admin.resp.AdminOrderPayPageResp;
import com.roncoo.education.user.service.admin.resp.AdminOrderPayViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-订单支付信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminOrderPayBiz extends BaseBiz {

    @NotNull
    private final OrderPayDao dao;

    /**
     * 订单支付信息表分页
     *
     * @param req 订单支付信息表分页查询参数
     * @return 订单支付信息表分页查询结果
     */
    public Result<Page<AdminOrderPayPageResp>> page(AdminOrderPayPageReq req) {
        OrderPayExample example = new OrderPayExample();
        Criteria c = example.createCriteria();
        Page<OrderPay> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminOrderPayPageResp> respPage = PageUtil.transform(page, AdminOrderPayPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 订单支付信息表添加
     *
     * @param req 订单支付信息表
     * @return 添加结果
     */
    public Result<String> save(AdminOrderPaySaveReq req) {
        OrderPay record = BeanUtil.copyProperties(req, OrderPay.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 订单支付信息表查看
     *
     * @param id 主键ID
     * @return 订单支付信息表
     */
    public Result<AdminOrderPayViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminOrderPayViewResp.class));
    }

    /**
     * 订单支付信息表修改
     *
     * @param req 订单支付信息表修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminOrderPayEditReq req) {
        OrderPay record = BeanUtil.copyProperties(req, OrderPay.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 订单支付信息表删除
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
