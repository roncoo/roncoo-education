package com.roncoo.education.course.callback.biz;

import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.JsonUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.impl.polyv.PolyvVodUtil;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackEventTypeEnum;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackVodUpload;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VideoConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 课程分类
 *
 * @author wujing
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PolyvCallbackBiz extends BaseBiz {

    @NotNull
    private final VodCommonBiz vodCommonBiz;

    @NotNull
    private final IFeignSysConfig feignSysConfig;

    public String polyvUpload(CallbackVodUpload callbackVodUpload) {
        log.warn("保利威-上传回调，{}", JsonUtil.toJsonString(callbackVodUpload));

        VideoConfig videoConfig = feignSysConfig.getVideo();
        String sign = PolyvVodUtil.getCallbackSign(BeanUtil.beanToStringMap(callbackVodUpload), videoConfig.getPolyvSecretKey());
        if (!callbackVodUpload.getSign().equals(sign)) {
            log.error("保利威视--点播上传回调--验签失败 {}", JsonUtil.toJsonString(callbackVodUpload));
            return FAIL;
        }

        if (CallbackEventTypeEnum.PASS.getCode().equals(callbackVodUpload.getType())) {
            // 视频审核完成处理
            vodCommonBiz.completeUpload(callbackVodUpload.getVid(), videoConfig);
        }
        return SUCCESS;
    }
}
