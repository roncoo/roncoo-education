package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.service.admin.resp.AdminStatLogin;
import com.roncoo.education.user.service.admin.resp.AdminStatLoginResp;
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
    private final LogLoginDao logLoginDao;

    public Result<AdminStatLoginResp> statLogin(Integer dates) {
        AdminStatLoginResp resp = new AdminStatLoginResp();
        List<AdminStatLogin> respList = logLoginDao.statByDate(dates);
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

}
