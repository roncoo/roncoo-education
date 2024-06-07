package com.roncoo.education.common.video;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.roncoo.education.common.core.enums.VideoStatusEnum;
import com.roncoo.education.common.core.enums.VodPlatformEnum;
import com.roncoo.education.common.core.tools.JsonUtil;
import com.roncoo.education.common.video.impl.polyv.PolyvVodUtil;
import com.roncoo.education.common.video.impl.polyv.vod.resp.PolyvVideoInfoResp;
import com.roncoo.education.common.video.impl.priyun.PrivateYunVodUtil;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunInfoResp;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunPlayTokenRes;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunVideoClarityResp;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunVideoInfoResp;
import com.roncoo.education.common.video.req.VideoConfigReq;
import com.roncoo.education.common.video.req.VodPlayConfigReq;
import com.roncoo.education.common.video.resp.InfoResp;
import com.roncoo.education.common.video.resp.VodInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 视频点播工具类
 *
 * @author fengyw
 */
@Slf4j
public final class VodUtil {

    private static final String PRIVATEY_CALLBACK = "gateway/course/callback/priyun/vod/upload";
    private static final String PRIVATEY_AUTH = "gateway/course/callback/priyun/vod/auth";

    private VodUtil() {
    }

    /**
     * 视频云初始化
     *
     * @param req
     */
    public static void init(VideoConfigReq req) {
        if (VodPlatformEnum.PRIVATEY.getCode().equals(req.getVodPlatform())) {
            // 无需初始化
            return;
        }
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            // 设置视频回调地址
            String callbackUrl = req.getWebsiteDomain() + "gateway/course/callback/polyv/vod/upload";
            PolyvVodUtil.setCallback(req.getPolyvAppId(), req.getPolyvAppSecret(), callbackUrl);

            // 开启加密，使用web授权
            PolyvVodUtil.setPlaysafe(req.getPolyvUserId(), req.getPolyvSecretKey(), "1", "web");

            // 设置授权播放和防录屏跑马灯地址
            //String marqueeUrl = req.getWebsiteDomain() + "gateway/course/callback/polyv/vod/auth";
            //PolyvVodUtil.setMarquee(req.getPolyvAppId(), req.getPolyvAppSecret(), marqueeUrl);
            return;
        }
    }

    public static InfoResp getInfo(VideoConfigReq req) {
        InfoResp infoResp = new InfoResp();
        if (VodPlatformEnum.PRIVATEY.getCode().equals(req.getVodPlatform())) {
            if (req.getPriyUrl().length() > 2) {
                PrivateYunInfoResp privateYunInfoResp = PrivateYunVodUtil.getInfo(req.getPriyUrl(), req.getPriyAccessKeyId(), req.getPriyAccessKeySecret());
                infoResp.setUsedSpace(BigDecimal.valueOf(privateYunInfoResp.getStorage()).divide(BigDecimal.valueOf(1073741824L)).setScale(2, BigDecimal.ROUND_HALF_UP));
                return infoResp;
            }
        }
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            if (req.getPolyvUserId().length() > 2) {
                JSONObject result = PolyvVodUtil.getUserMain(req.getPolyvUserId(), req.getPolyvSecretKey());
                if (ObjectUtil.isNotNull(result)) {
                    infoResp.setTotalSpace(result.getBigDecimal("totalSpace").divide(BigDecimal.valueOf(1073741824L)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    infoResp.setUsedSpace(result.getBigDecimal("usedSpace").divide(BigDecimal.valueOf(1073741824L)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    infoResp.setTotalFlow(result.getBigDecimal("totalFlow").divide(BigDecimal.valueOf(1073741824L)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    infoResp.setUsedFlow(result.getBigDecimal("usedFlow").divide(BigDecimal.valueOf(1073741824L)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    //infoResp.setUniqueNo(result.getStr("email"));
                }
                return infoResp;
            }
        }
        return infoResp;
    }

    /**
     * 获取上传视频回调地址
     *
     * @param req
     * @return
     */
    public static String getCallbackUrl(VideoConfigReq req) {
        if (VodPlatformEnum.PRIVATEY.getCode().equals(req.getVodPlatform())) {
            return req.getWebsiteDomain() + PRIVATEY_CALLBACK;
        }
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            return PolyvVodUtil.getCallbackUrl(req.getPolyvAppId(), req.getPolyvAppSecret());
        }
        return "";
    }

    /**
     * 获取上传配置，web端上传视频
     *
     * @return json
     */
    public static String getUploadConfig(VideoConfigReq req) {
        if (VodPlatformEnum.PRIVATEY.getCode().equals(req.getVodPlatform())) {
            // 上传回调地址
            String callbackUrl = req.getWebsiteDomain() + PRIVATEY_CALLBACK;
            return JsonUtil.toJsonString(PrivateYunVodUtil.getUploadConfig(req.getPriyAccessKeyId(), req.getPriyAccessKeySecret(), req.getPriyUrl(), callbackUrl, ""));
        }
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            return JsonUtil.toJsonString(PolyvVodUtil.getUploadConfig(req.getPolyvUserId(), req.getPolyvSecretKey(), req.getPolyvWriteToken()));
        }
        return "";
    }

    /**
     * 获取视频信息
     *
     * @param req
     * @param vid
     * @return
     */
    public static VodInfoResp getVideoInfo(VideoConfigReq req, String vid) {
        VodInfoResp resp = new VodInfoResp();
        resp.setVodPlatform(req.getVodPlatform());
        resp.setVid(vid);
        if (VodPlatformEnum.PRIVATEY.getCode().equals(req.getVodPlatform())) {
            PrivateYunVideoInfoResp videoInfoResp = PrivateYunVodUtil.getVideoInfo(req.getPriyUrl(), req.getPriyAccessKeyId(), req.getPriyAccessKeySecret(), vid, "");
            if (ObjectUtil.isNotNull(videoInfoResp)) {
                if (videoInfoResp.getVideoStatus().equals("5")) {
                    // 视频状态(1:上传中;2:上传失败;3:转码中;4:转码失败;5:转码完成)
                    resp.setVideoStatusEnum(VideoStatusEnum.SUCCES);
                    resp.setDuration(Integer.valueOf(videoInfoResp.getVideoDuration()));
                    resp.setCoverUrl(videoInfoResp.getVideoThumbnail());
                    resp.setFileSize(Long.valueOf(videoInfoResp.getVideoSize()));
                }
            }
            return resp;
        }
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            List<PolyvVideoInfoResp> responseList = PolyvVodUtil.getVideoInfo(Collections.singletonList(vid), req.getPolyvUserId(), req.getPolyvSecretKey());
            if (CollectionUtil.isEmpty(responseList)) {
                return null;
            }
            PolyvVideoInfoResp.BasicInfo basicInfo = responseList.get(0).getBasicInfo();
            // 视频状态码(60/61：已发布、10：等待编码、20：正在编码、40：编码失败、50：等待审核、51：审核不通过、-1：已删除)
            if (basicInfo.getStatus() == 60 || basicInfo.getStatus() == 61) {
                // 审核通过
                resp.setVideoStatusEnum(VideoStatusEnum.SUCCES);
                resp.setDuration(basicInfo.getDuration());
                resp.setCoverUrl(basicInfo.getCoverURL());
                resp.setFileSize(basicInfo.getSize());
            } else {
                log.warn("保利威-getVideoInfo={}", JsonUtil.toJsonString(basicInfo));
            }
            return resp;
        }
        return resp;
    }

    /**
     * 删除视频
     *
     * @param req
     * @param vid
     * @return
     */
    public static void deleteVideo(VideoConfigReq req, String vid) {
        if (VodPlatformEnum.PRIVATEY.getCode().equals(req.getVodPlatform())) {
            PrivateYunVodUtil.delete(req.getPriyUrl(), req.getPriyAccessKeyId(), req.getPriyAccessKeySecret(), vid, "");
            return;
        }
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            PolyvVodUtil.delete(vid, req.getPolyvUserId(), req.getPolyvSecretKey());
            return;
        }
    }

    /**
     * 获取视频播放配置，返回播放器需要的参数
     *
     * @param req
     * @param playConfigReq
     * @return
     */
    public static String getPlayConfig(VideoConfigReq req, VodPlayConfigReq playConfigReq) {
        if (VodPlatformEnum.PRIVATEY.getCode().equals(req.getVodPlatform())) {
            // 播放授权地址
            String authUrl = req.getWebsiteDomain() + PRIVATEY_AUTH;
            PrivateYunPlayTokenRes tokenResponse = PrivateYunVodUtil.token(req.getPriyUrl(), authUrl, req.getPriyAccessKeyId(), req.getPriyAccessKeySecret(), playConfigReq.getVid(), "", playConfigReq.getExpiresIn().toString(), playConfigReq.getViewerId());
            if (tokenResponse == null) {
                return "";
            }
            PrivateYunVideoClarityResp playUrls = tokenResponse.getVideoClarityResp();
            if (ObjectUtil.isNotNull(playUrls)) {
                if (StringUtils.hasText(playUrls.getHdUrl())) {
                    playUrls.setHdUrl(playUrls.getHdUrl() + "?token=" + tokenResponse.getToken());
                }
                if (StringUtils.hasText(playUrls.getSdUrl())) {
                    playUrls.setSdUrl(playUrls.getSdUrl() + "?token=" + tokenResponse.getToken());
                }
            }
            return JsonUtil.toJsonString(playUrls);
        }
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            // 返回播放配置
            return JsonUtil.toJsonString(PolyvVodUtil.getSignForPlay(req.getPolyvUserId(), req.getPolyvSecretKey(), playConfigReq));
        }
        return "";
    }
}
