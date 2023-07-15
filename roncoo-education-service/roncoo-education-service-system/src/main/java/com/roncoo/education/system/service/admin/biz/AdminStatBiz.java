package com.roncoo.education.system.service.admin.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.VodUtil;
import com.roncoo.education.common.video.resp.InfoResp;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import com.roncoo.education.system.service.biz.SysConfigCommonBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@Slf4j
@Component
public class AdminStatBiz extends BaseBiz {

    @Autowired
    private SysConfigCommonBiz sysConfigCommonBiz;

    public Result<InfoResp> vod() {
        VodConfig vodConfig = sysConfigCommonBiz.getSysConfig(VodConfig.class);
        InfoResp resp = VodUtil.getInfo(vodConfig);
        return Result.success(resp);
    }
}
