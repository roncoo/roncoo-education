package com.roncoo.education.system.service.admin.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.VodUtil;
import com.roncoo.education.common.video.resp.InfoResp;
import com.roncoo.education.system.feign.interfaces.vo.VideoConfig;
import com.roncoo.education.system.service.biz.SysConfigCommonBiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminStatBiz extends BaseBiz {

    @NotNull
    private final SysConfigCommonBiz sysConfigCommonBiz;

    public Result<InfoResp> vod() {
        VideoConfig videoConfig = sysConfigCommonBiz.getSysConfig(VideoConfig.class);
        InfoResp resp = VodUtil.getInfo(videoConfig);
        return Result.success(resp);
    }
}
