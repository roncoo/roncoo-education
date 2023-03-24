package com.roncoo.education.system.service.admin.biz;

import cn.hutool.json.JSONObject;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.VodPlatformEnum;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.impl.polyv.PolyvVodUtil;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import com.roncoo.education.system.service.admin.resp.StatVodResp;
import com.roncoo.education.system.service.biz.SysConfigCommonBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@Slf4j
@Component
public class AdminStatBiz extends BaseBiz {

    @Autowired
    private SysConfigDao dao;
    @Autowired
    private SysConfigCommonBiz sysConfigCommonBiz;

    public Result<StatVodResp> vod() {
        StatVodResp resp = new StatVodResp();
        VodConfig vodConfig = sysConfigCommonBiz.getSysConfig(VodConfig.class);
        try {
            if (VodPlatformEnum.POLYV.getCode().equals(vodConfig.getVodPlatform())) {
                JSONObject result = PolyvVodUtil.getUserMain(vodConfig.getPolyvUserId(), vodConfig.getPolyvSecretKey());
                resp.setTotalSpace(result.getBigDecimal("totalSpace").divide(BigDecimal.valueOf(1024 * 1024 * 1024)).setScale(2, BigDecimal.ROUND_HALF_UP));
                resp.setUsedSpace(result.getBigDecimal("usedSpace").divide(BigDecimal.valueOf(1024 * 1024 * 1024)).setScale(2, BigDecimal.ROUND_HALF_UP));
                resp.setTotalFlow(result.getBigDecimal("totalFlow").divide(BigDecimal.valueOf(1024 * 1024 * 1024)).setScale(2, BigDecimal.ROUND_HALF_UP));
                resp.setUsedFlow(result.getBigDecimal("usedFlow").divide(BigDecimal.valueOf(1024 * 1024 * 1024)).setScale(2, BigDecimal.ROUND_HALF_UP));
                // resp.setEmail(result.getStr("email"));
            }
        } catch (Exception e) {
            log.error("获取异常", e);
        }
        return Result.success(resp);
    }
}
