package com.roncoo.education.user.service.auth.biz;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.OrderStatusEnum;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.user.service.auth.req.AuthOrderInfoReq;
import com.roncoo.education.user.service.auth.resp.AuthOrderInfoResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AUTH-订单信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthOrderInfoBiz extends BaseBiz {

    @NotNull
    private final OrderInfoDao dao;
    @NotNull
    private final IFeignCourse feignCourse;

    public Result<Page<AuthOrderInfoResp>> listForPage(AuthOrderInfoReq req) {
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria c = example.createCriteria();
        c.andUserIdEqualTo(ThreadContext.userId());
        if (req.getOrderStatus() == null) {
            // 默认关闭状态下的订单不显示
            c.andOrderStatusNotEqualTo(OrderStatusEnum.CLOSE.getCode());
        } else {
            c.andOrderStatusEqualTo(req.getOrderStatus());
        }
        example.setOrderByClause(" id desc ");
        Page<OrderInfo> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AuthOrderInfoResp> respPage = PageUtil.transform(page, AuthOrderInfoResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            // 获取课程信息
            List<Long> courseIds = respPage.getList().stream().map(AuthOrderInfoResp::getCourseId).collect(Collectors.toList());
            Map<Long, String> courseNames = feignCourse.listByIds(courseIds).stream().collect(Collectors.toMap(CourseViewVO::getId, CourseViewVO::getCourseName));
            for (AuthOrderInfoResp resp : respPage.getList()) {
                resp.setCourseName(courseNames.get(resp.getCourseId()));
            }
        }
        return Result.success(respPage);
    }
}
