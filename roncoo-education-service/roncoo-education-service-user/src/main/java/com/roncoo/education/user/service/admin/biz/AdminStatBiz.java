package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.UsersLogDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample;
import com.roncoo.education.user.service.admin.resp.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 注册登录统计
 *
 * @author wujing
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminStatBiz extends BaseBiz {

    @NotNull
    private final UsersLogDao usersLogDao;

    @NotNull
    private final UsersDao usersDao;

    @NotNull
    private final IFeignCourse feignCourse;

    @NotNull
    private final OrderInfoDao orderInfoDao;

    public Result<AdminStatLoginResp> statLogin(Integer dates) {
        AdminStatLoginResp resp = new AdminStatLoginResp();
        List<AdminStatLogin> respList = usersLogDao.statByDate(dates);
        if (CollUtil.isNotEmpty(respList)) {
            resp.setDateList(respList.stream().map(AdminStatLogin::getDates).distinct().collect(Collectors.toList()));
            Map<String, Long> loginMap = respList.stream().filter(s -> s.getLoginStatus().equals(1)).collect(Collectors.toMap(s -> s.getDates(), s -> s.getLogins()));
            Map<String, Long> registerMap = respList.stream().filter(s -> s.getLoginStatus().equals(2)).collect(Collectors.toMap(s -> s.getDates(), s -> s.getLogins()));
            List<Long> loginList = new ArrayList<>();
            List<Long> registerList = new ArrayList<>();
            for (String data : resp.getDateList()) {
                loginList.add(loginMap.get(data) == null ? 0 : loginMap.get(data));
                registerList.add(registerMap.get(data) == null ? 0 : registerMap.get(data));
            }
            resp.setLoginList(loginList);
            resp.setRegisterList(registerList);
        }
        return Result.success(resp);
    }

    public Result<AdminStatDataResp> statData() {
        AdminStatDataResp resp = new AdminStatDataResp();
        String tomorrow = DateUtil.formatDate(DateUtil.tomorrow());
        String yesterday = DateUtil.formatDate(DateUtil.yesterday());
        List<AdminOrderStat> stats = orderInfoDao.stat(yesterday, tomorrow);
        if (CollUtil.isNotEmpty(stats)) {
            Map<String, AdminOrderStat> maps = stats.stream().collect(Collectors.toMap(s -> s.getDates(), s -> s));
            AdminOrderStat todayMap = maps.get(DateUtil.today());
            if (ObjectUtil.isNotNull(todayMap)) {
                resp.setTodayOrder(todayMap.getOrders());
                resp.setTodayMoney(todayMap.getMoneys());
            }
            AdminOrderStat yesterdayMap = maps.get(yesterday);
            if (ObjectUtil.isNotNull(yesterdayMap)) {
                resp.setYesterdayOrder(yesterdayMap.getOrders());
                resp.setYesterdayMoney(yesterdayMap.getMoneys());
            }
        }
        resp.setUserSum(usersDao.count(new UsersExample()));
        resp.setCourseSum(feignCourse.count());
        AdminOrderInfoStatResp statResp = orderInfoDao.stat(null);
        resp.setOrderSum(statResp.getCourseBuySum());
        resp.setOrderMoney(statResp.getCourseBuyMoney());
        return Result.success(resp);
    }

}
