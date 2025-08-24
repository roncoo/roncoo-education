package com.roncoo.education.course.callback.biz;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.roncoo.education.common.core.enums.LiveStatusEnum;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.tools.JsonUtil;
import com.roncoo.education.common.tools.Md5Util;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.common.video.impl.polyv.PolyvVodUtil;
import com.roncoo.education.common.video.impl.polyv.live.*;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackEventTypeEnum;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackVodAuth;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackVodAuthResult;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackVodUpload;
import com.roncoo.education.course.dao.LiveDao;
import com.roncoo.education.course.dao.LiveLogDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Live;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveLog;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VideoConfig;
import com.roncoo.education.user.feign.interfaces.IFeignUsers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

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
    private final LiveDao liveDao;
    @NotNull
    private final LiveLogDao liveLogDao;
    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final IFeignUsers feignUsers;

    /**
     * 点播上传回调
     */
    public String vodUpload(CallbackVodUpload callbackVodUpload) {
        log.warn("保利威--上传回调--回调参数：{}", JsonUtil.toJsonString(callbackVodUpload));

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

    public String vodAuth(CallbackVodAuth callbackVodAuth) {
        log.info("保利威--点播授权播放--回调参数：{}", JsonUtil.toJsonString(callbackVodAuth));
        // 这里可以进行播放授权
        CallbackVodAuthResult result = new CallbackVodAuthResult();
        result.setStatus(1);
        return vodAuthResponse(callbackVodAuth, result);
    }

    private String vodAuthResponse(CallbackVodAuth vodAuth, CallbackVodAuthResult result) {
        VideoConfig videoConfig = feignSysConfig.getVideo();
        // 判断是否开启跑马灯功能
        if (videoConfig.getVodEnableMarquee()) {
            result.setShow("on");
        }
        String sign = "vid=" + vodAuth.getVid() + "&secretkey=" + videoConfig.getPolyvSecretKey() + "&username=" + result.getUsername() +
                "&code=" + vodAuth.getCode().replace(" ", "+") + "&status=" + result.getStatus() +
                "&t=" + vodAuth.getT() + "&msg=" + result.getMsg() + "&fontSize=" + result.getFontSize() +
                "&fontColor=" + result.getFontColor() + "&speed=" + result.getSpeed() + "&filter=" + result.getFilter() +
                "&setting=" + result.getSetting() + "&alpha=" + result.getAlpha() + "&filterAlpha=" + result.getFilterAlpha() +
                "&filterColor=" + result.getFilterColor() + "&blurX=" + result.getBlurX() + "&blurY=" + result.getBlurY() +
                "&interval=" + result.getInterval() + "&lifeTime=" + result.getLifeTime() + "&tweenTime=" + result.getTweenTime() +
                "&strength=" + result.getStrength() + "&show=" + result.getShow();
        result.setSign(Md5Util.md5(sign));
        return vodAuth.getCallback() + "(" + JsonUtil.toJsonString(result) + ")";
    }

    /**
     * 直播状态回调
     */
    public String liveStatus(CallbackLiveStatus callbackLiveStatus) {
        log.warn("保利威--直播状态回调--回调参数：{}", JsonUtil.toJsonString(callbackLiveStatus));
        // 根据频道号查找课时
        Live live = liveDao.getByChannelId(callbackLiveStatus.getChannelId());
        if (live == null) {
            log.warn("该直播课时不存在，LiveStatus={}", JsonUtil.toJsonString(callbackLiveStatus));
            return SUCCESS;
        }

        if (callbackLiveStatus.getStatus().equals("live")) {
            // 正在直播

            // 添加直播记录
            LiveLog liveLog = new LiveLog();
            liveLog.setLiveId(live.getId());
            liveLog.setLivePlatform(live.getLivePlatform());
            liveLog.setLiveStatus(LiveStatusEnum.LIVING.getCode());
            liveLog.setChannelId(callbackLiveStatus.getChannelId());
            liveLog.setSessionId(callbackLiveStatus.getSessionId());
            liveLog.setStartTime(LocalDateTimeUtil.of(callbackLiveStatus.getStartTime()));
            liveLog.setPlaybackSave(live.getPlaybackSave());
            liveLogDao.save(liveLog);

            // 更新直播状态
            Live liveStatus = new Live();
            liveStatus.setId(live.getId());
            liveStatus.setLiveStatus(LiveStatusEnum.LIVING.getCode());
            liveDao.updateById(liveStatus);
        } else if (callbackLiveStatus.getStatus().equals("end")) {
            // 直播结束
            LiveLog liveLog = liveLogDao.getBySessionId(callbackLiveStatus.getSessionId());
            if (liveLog != null) {
                // 更新回放状态
                LiveLog liveLogStatus = new LiveLog();
                liveLogStatus.setId(liveLog.getId());
                liveLogStatus.setLiveStatus(LiveStatusEnum.PLAYBACK.getCode());
                liveLogStatus.setEndTime(LocalDateTimeUtil.of(callbackLiveStatus.getEndTime()));
                liveLogDao.updateById(liveLogStatus);
            }

            // 更新直播状态
            Live liveStatus = new Live();
            liveStatus.setId(live.getId());
            liveStatus.setLiveStatus(LiveStatusEnum.PLAYBACK.getCode());
            liveDao.updateById(liveStatus);
        }
        return SUCCESS;
    }

    public String liveAuth(CallbackLiveAuth callbackLiveAuth) {
        log.warn("保利威--直播观看授权--回调参数：{}", JsonUtil.toJsonString(callbackLiveAuth));
        CallbackLiveAuthResult result = new CallbackLiveAuthResult();
        result.setUserid(callbackLiveAuth.getUserid());
        return JsonUtil.toJsonString(result);
    }

    public String livePlayback(CallbackLivePlayback callbackLivePlayback) {
        log.warn("保利威--直播回放生成--回调参数：{}", JsonUtil.toJsonString(callbackLivePlayback));
        return SUCCESS;
    }

    public String liveConvert(CallbackLiveConvert callbackLiveConvert) {
        log.warn("保利威--直播回放转存--回调参数：{}", JsonUtil.toJsonString(callbackLiveConvert));
        return SUCCESS;
    }

    public String liveRefashion(CallbackLiveRefashion callbackLiveRefashion) {
        log.warn("保利威--直播课件重制--回调参数：{}", JsonUtil.toJsonString(callbackLiveRefashion));
        return SUCCESS;
    }

}
