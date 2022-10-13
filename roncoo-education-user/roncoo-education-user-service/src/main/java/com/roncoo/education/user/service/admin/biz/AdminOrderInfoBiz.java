package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoEditReq;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoPageReq;
import com.roncoo.education.user.service.admin.req.AdminOrderInfoSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoPageResp;
import com.roncoo.education.user.service.admin.resp.AdminOrderInfoViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @NotNull
    private final IFeignCourse feignCourse;

    /**
     * 订单信息表分页
     *
     * @param req 订单信息表分页查询参数
     * @return 订单信息表分页查询结果
     */
    public Result<Page<AdminOrderInfoPageResp>> page(AdminOrderInfoPageReq req) {
        OrderInfoExample example = new OrderInfoExample();
        Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getOrderNo()) && req.getOrderNo().compareTo(0L) > 0) {
            c.andOrderNoEqualTo(req.getOrderNo());
        }
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileLike(PageUtil.rightLike(req.getMobile()));
        }
        example.setOrderByClause("id desc");
        Page<OrderInfo> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminOrderInfoPageResp> respPage = PageUtil.transform(page, AdminOrderInfoPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> courseIdList = respPage.getList().stream().map(AdminOrderInfoPageResp::getCourseId).collect(Collectors.toList());
            Map<Long, CourseViewVO> courseViewVOMap = feignCourse.listByIds(courseIdList).stream().collect(Collectors.toMap(courseViewVO -> courseViewVO.getId(), courseViewVO -> courseViewVO));
            for (AdminOrderInfoPageResp resp : respPage.getList()) {
                resp.setCourseViewVO(courseViewVOMap.get(resp.getCourseId()));
                // 脱敏处理
                resp.setMobile(DesensitizedUtil.mobilePhone(resp.getMobile()));
            }
        }
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
