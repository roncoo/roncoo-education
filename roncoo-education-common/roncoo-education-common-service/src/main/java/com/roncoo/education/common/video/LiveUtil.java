package com.roncoo.education.common.video;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.enums.LivePlatformEnum;
import com.roncoo.education.common.core.enums.LiveSceneEnum;
import com.roncoo.education.common.core.enums.MarqueeTypeEnum;
import com.roncoo.education.common.tools.JsonUtil;
import com.roncoo.education.common.video.impl.polyv.PolyvLiveUtil;
import com.roncoo.education.common.video.impl.polyv.PolyvSignUtil;
import com.roncoo.education.common.video.impl.polyv.live.request.ChannelCreateRequest;
import com.roncoo.education.common.video.impl.polyv.live.response.ChannelCreateResponse;
import com.roncoo.education.common.video.impl.polyv.live.response.ChannelWatchConditionResponse;
import com.roncoo.education.common.video.req.LiveBroadcastReq;
import com.roncoo.education.common.video.req.LiveChannelReq;
import com.roncoo.education.common.video.req.LiveWatchReq;
import com.roncoo.education.common.video.req.VideoConfigReq;
import com.roncoo.education.common.video.resp.LiveChannelResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author fengyw
 */
@Slf4j
public final class LiveUtil {

    private LiveUtil() {
    }

    /**
     * 视频云初始化，注意：重制功能都需要联系视频云单独开通
     *
     * @param req
     */
    public static void init(VideoConfigReq req) {
        if (LivePlatformEnum.POLYV.getCode().equals(req.getLivePlatform())) {
            if (StringUtils.hasText(req.getPolyvAppId())) {
                // 直播状态回调
                String liveStatusUrl = req.getWebsiteDomain() + "gateway/course/callback/polyv/live/status";
                // 回放生成回调
                String recordCallback = req.getWebsiteDomain() + "gateway/course/callback/polyv/live/playback";
                // 转存成功回调URL
                String playbackConvert = req.getWebsiteDomain() + "gateway/course/callback/polyv/live/convert";
                // 重制课件成功回调URL
                String coursewareRefashion = req.getWebsiteDomain() + "gateway/course/callback/polyv/live/refashion";

                PolyvLiveUtil.globalSettingCallbackUpdate(req.getPolyvAppId(), req.getPolyvAppSecret(), recordCallback, "last", "mp4", playbackConvert, null, coursewareRefashion, liveStatusUrl, null, null, null);
            }
        }
    }

    /**
     * 创建频道号
     *
     * @param req
     * @param configReq
     * @return
     */
    public static LiveChannelResp getLiveChannel(VideoConfigReq req, LiveChannelReq channelReq) {
        LiveChannelResp resp = new LiveChannelResp();
        resp.setLiveModel(channelReq.getLiveModel());
        if (LivePlatformEnum.POLYV.getCode().equals(req.getLivePlatform())) {
            ChannelCreateRequest request = new ChannelCreateRequest();
            if (LiveSceneEnum.LARGE_CLASS.equals(channelReq.getLiveScene())) {
                request.setNewScene("topclass");
            } else if (LiveSceneEnum.SEMINAR.equals(channelReq.getLiveScene())) {
                request.setNewScene("seminar");
            } else {
                log.error("保利威-暂不支持该场景：{}", JsonUtil.toJsonString(channelReq));
                throw new BaseException("暂不支持该场景");
            }
            request.setName(channelReq.getLiveName());
            request.setLinkMicLimit(channelReq.getLinkMicLimit());
            request.setPureRtcEnabled(channelReq.getWebrtc() ? "Y" : "N");
            request.setTemplate(channelReq.getLiveModel().getDesc());
            // 设置频道号密码
            request.setChannelPasswd(RandomUtil.randomNumbers(6));
            // 创建频道号
            ChannelCreateResponse response = PolyvLiveUtil.createChannel(request, req.getPolyvAppId(), req.getPolyvAppSecret());
            if (ObjectUtil.isNull(response)) {
                log.error("保利威-创建直播频道失败", JsonUtil.toJsonString(request));
                throw new BaseException("创建直播频道失败");
            }
            resp.setChannelId(response.getChannelId().toString());
            resp.setChannelPwd(request.getChannelPasswd());
            return resp;
        }
        return null;
    }

    /**
     * 获取开播链接，讲师开播链接
     *
     * @param req
     * @param urlReq
     * @return
     */
    public static String getLiveBroadcastUrl(VideoConfigReq req, LiveBroadcastReq broadcastReq) {
        if (LivePlatformEnum.POLYV.getCode().equals(req.getLivePlatform())) {
            if (broadcastReq.getLiveScene().equals(LiveSceneEnum.LARGE_CLASS)) {
                if (broadcastReq.getEnableMarquee()) {
                    // 跑马灯类型
                    if (MarqueeTypeEnum.FIXED_VALUE.getCode().equals(broadcastReq.getMarqueeTypeEnum())) {
                        // 固定值
                        PolyvLiveUtil.channelRecordSetting(broadcastReq.getChannelId(), req.getPolyvAppId(), req.getPolyvAppSecret(), "marquee", "fixed", broadcastReq.getMarqueeContent(), "30", null);
                    } else {
                        // 动态值：这里设置为空，具体的内容由回调设置
                        PolyvLiveUtil.channelRecordSetting(broadcastReq.getChannelId(), req.getPolyvAppId(), req.getPolyvAppSecret(), "marquee", "nickname", null, "30", null);
                    }
                }
                // 大班课
                broadcastReq.setExternalUrl(String.format("%s" + "gateway/course/callback/polyv/live/auth", req.getWebsiteDomain()));
                return PolyvLiveUtil.getLargeClassBroadcastUrl(req, broadcastReq);
            }
        }
        return "";
    }

    /**
     * 获取观看链接，用户观看
     *
     * @param req
     * @param watchReq
     * @return
     */
    public static String getLiveWatchUrl(VideoConfigReq req, LiveWatchReq watchReq) {
        if (LivePlatformEnum.POLYV.getCode().equals(req.getLivePlatform())) {
            // 获取频道观看条件
            List<ChannelWatchConditionResponse> conditionResponseList = PolyvLiveUtil.getWatchCondition(watchReq.getChannelId(), req.getPolyvAppId(), req.getPolyvAppSecret());
            // 处理观看地址
            Long ts = System.currentTimeMillis();
            return "{domain}watch/{channelId}?userid={userid}&ts={ts}&sign={sign}".replace("{domain}", req.getPolyvLiveDomain()).replace("{channelId}", watchReq.getChannelId()).replace("{userid}", watchReq.getUserId().toString()).replace("{ts}", ts.toString()).replace("{sign}", PolyvSignUtil.getExternalAuthSign(conditionResponseList.get(0).getExternalKey(), watchReq.getUserId().toString(), ts));
        }
        return "";
    }

}
