package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.IPUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.SysLogDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.system.feign.interfaces.qo.FeignSysLogQO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * 系统配置
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignSysLogBiz extends BaseBiz {

    @NotNull
    private final SysLogDao dao;

    public int save(FeignSysLogQO qo) {
        if (StringUtils.hasText(qo.getLoginIp())) {
            IPUtil.IpInfo ipInfo = IPUtil.getIpInfo(qo.getLoginIp());
            if (ipInfo != null) {
                qo.setProvince(ipInfo.getPro());
                qo.setCity(ipInfo.getCity());
            }
        }
        return dao.save(BeanUtil.copyProperties(qo, SysLog.class));
    }
}
