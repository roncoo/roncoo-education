package com.roncoo.education.course.callback.biz;

import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.impl.polyv.PolyvVodUtil;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackEventTypeEnum;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackVodUpload;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
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
        log.warn("保利威-上传回调，{}", JSUtil.toJsonString(callbackVodUpload));

        VodConfig vodConfig = feignSysConfig.getVod();
        String sign = PolyvVodUtil.getCallbackSign(BeanUtil.beanToStringMap(callbackVodUpload), vodConfig.getPolyvSecretKey());
        if (!callbackVodUpload.getSign().equals(sign)) {
            log.error("保利威视--点播上传回调--验签失败 {}", JSUtil.toJsonString(callbackVodUpload));
            return FAIL;
        }

        if (CallbackEventTypeEnum.PASS.getCode().equals(callbackVodUpload.getType())) {
            // 视频审核完成处理
            vodCommonBiz.completeUpload(callbackVodUpload.getVid(), vodConfig);
        }
        return SUCCESS;
    }
}
