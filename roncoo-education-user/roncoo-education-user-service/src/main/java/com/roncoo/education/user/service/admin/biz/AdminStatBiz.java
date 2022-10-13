package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.service.admin.resp.AdminStatLogin;
import com.roncoo.education.user.service.admin.resp.AdminStatLoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@Slf4j
@Component
public class AdminStatBiz extends BaseBiz {

    @Autowired
    private LogLoginDao logLoginDao;

    public Result<AdminStatLoginResp> statLogin(Integer dates) {
        AdminStatLoginResp resp = new AdminStatLoginResp();
        List<AdminStatLogin> respList = logLoginDao.statByDate(dates);
        if (CollUtil.isNotEmpty(respList)) {
            resp.setDateList(respList.stream().map(AdminStatLogin::getDates).collect(Collectors.toList()));
            resp.setLoginList(respList.stream().map(AdminStatLogin::getLogins).collect(Collectors.toList()));
        }
        return Result.success(resp);
    }

}
