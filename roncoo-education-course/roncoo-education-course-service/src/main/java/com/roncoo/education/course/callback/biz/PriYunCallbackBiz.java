package com.roncoo.education.course.callback.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.impl.priyun.enums.PrivateYunVideoStatusEnum;
import com.roncoo.education.common.video.impl.priyun.req.PrivateYunVodAuth;
import com.roncoo.education.common.video.impl.priyun.req.PrivateYunVodUpload;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 回调业务层
 *
 * @author fengyw
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PriYunCallbackBiz extends BaseBiz {

    @NotNull
    private final VodCommonBiz vodCommonBiz;

    @NotNull
    private final IFeignSysConfig feignSysConfig;

    /**
     * 上传通知
     *
     * @param vodUpload
     * @return
     */
    public String vodUpload(PrivateYunVodUpload vodUpload) {
        VodConfig vodConfig = feignSysConfig.getVod();
        if (PrivateYunVideoStatusEnum.COMPLETE.getCode().equals(vodUpload.getVideoStatus())) {
            // 视频处理完成
            vodCommonBiz.completeUpload(vodUpload.getVideoVid(), vodConfig);
        }
        return SUCCESS;
    }

    /**
     * 授权播放
     *
     * @param vodAuth
     * @return
     */
    public String auth(PrivateYunVodAuth vodAuth) {
        return PASS;
    }
}
