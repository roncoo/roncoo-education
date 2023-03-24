package com.roncoo.education.common.video;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.enums.VodPlatformEnum;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.video.impl.polyv.PolyvVodUtil;
import com.roncoo.education.common.video.impl.polyv.vod.resp.PolyvVideoInfoResp;
import com.roncoo.education.common.video.impl.priyun.PrivateYunVodUtil;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunPlayTokenRes;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunVideoClarityResp;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunVideoInfoResp;
import com.roncoo.education.common.video.req.VideoConfig;
import com.roncoo.education.common.video.req.VodPlayConfigReq;
import com.roncoo.education.common.video.resp.VodInfoResp;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * 视频点播工具类
 *
 * @author fengyw
 */
@Slf4j
public final class VodUtil {

    private VodUtil() {
    }

    /**
     * 视频云初始化
     *
     * @param req
     */
    public static void init(VideoConfig req) {
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            // 设置视频回调地址
            String callbackUrl = req.getWebsiteDomain() + "gateway/course/api/callback/polyv/upload";
            PolyvVodUtil.setCallback(req.getPolyvAppId(), req.getPolyvAppSecret(), callbackUrl);

            // 开启加密，使用web授权
            PolyvVodUtil.setPlaysafe(req.getPolyvUserId(), req.getPolyvSecretKey(), "1", "web");

            // 设置授权播放和防录屏跑马灯地址
            String marqueeUrl = req.getWebsiteDomain() + "gateway/course/api/callback/polyv/vod/auth";
            PolyvVodUtil.setMarquee(req.getPolyvAppId(), req.getPolyvAppSecret(), marqueeUrl);
        } else if (VodPlatformEnum.PRIVATE.getCode().equals(req.getVodPlatform())) {
            // 无需初始化
        }
    }

    /**
     * 获取上传配置，web端上传视频
     *
     * @return json
     */
    public static String getUploadConfig(VideoConfig req) {
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            return JSUtil.toJsonString(PolyvVodUtil.getUploadConfig(req.getPolyvUserId(), req.getPolyvSecretKey(), req.getPolyvWriteToken()));
        } else if (VodPlatformEnum.PRIVATE.getCode().equals(req.getVodPlatform())) {
            // 上传回调地址
            String callbackUrl = req.getWebsiteDomain() + "gateway/course/api/callback/priyun/vod/upload";
            return JSUtil.toJsonString(PrivateYunVodUtil.getUploadConfig(req.getPriYunAccessKeyId(), req.getPriYunAccessKeySecret(), req.getPriYunUrl(), callbackUrl, ""));
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
    public static VodInfoResp getVideoInfo(VideoConfig req, String vid) {
        VodInfoResp resp = new VodInfoResp();
        resp.setVodPlatform(req.getVodPlatform());
        resp.setVid(vid);
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            List<PolyvVideoInfoResp> responseList = PolyvVodUtil.getVideoInfo(Collections.singletonList(vid), req.getPolyvUserId(), req.getPolyvSecretKey());
            if (CollectionUtil.isEmpty(responseList)) {
                return null;
            }
            PolyvVideoInfoResp.BasicInfo basicInfo = responseList.get(0).getBasicInfo();
            // 视频状态码(60/61：已发布、10：等待编码、20：正在编码、40：编码失败、50：等待审核、51：审核不通过、-1：已删除)
            if (basicInfo.getStatus() == 60 || basicInfo.getStatus() == 61) {
                // 审核通过
                resp.setDuration(basicInfo.getDuration());
                resp.setCoverUrl(basicInfo.getCoverURL());
                resp.setFileSize(basicInfo.getSize());
            } else {
                log.warn("保利威-getVideoInfo={}", JSUtil.toJsonString(basicInfo));
            }
        } else if (VodPlatformEnum.PRIVATE.getCode().equals(req.getVodPlatform())) {
            PrivateYunVideoInfoResp videoInfoResp = PrivateYunVodUtil.getVideoInfo(req.getPriYunUrl(), req.getPriYunAccessKeyId(), req.getPriYunAccessKeySecret(), vid, "");
            if (ObjectUtil.isNotNull(videoInfoResp)) {
                resp.setDuration(Integer.valueOf(videoInfoResp.getVideoDuration()));
                resp.setCoverUrl(videoInfoResp.getVideoThumbnail());
                resp.setFileSize(Long.valueOf(videoInfoResp.getVideoSize()));
            }
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
    public static void deleteVideo(VideoConfig req, String vid) {
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            PolyvVodUtil.delete(vid, req.getPolyvUserId(), req.getPolyvSecretKey());
        } else if (VodPlatformEnum.PRIVATE.getCode().equals(req.getVodPlatform())) {
            PrivateYunVodUtil.delete(req.getPriYunUrl(), req.getPriYunAccessKeyId(), req.getPriYunAccessKeySecret(), vid, "");
        }
    }

    /**
     * 获取视频播放配置，返回播放器需要的参数
     *
     * @param req
     * @param playConfigReq
     * @return
     */
    public static String getPlayConfig(VideoConfig req, VodPlayConfigReq playConfigReq) {
        if (VodPlatformEnum.POLYV.getCode().equals(req.getVodPlatform())) {
            // 返回播放配置
            return JSUtil.toJsonString(PolyvVodUtil.getSignForPlay(req.getPolyvUserId(), req.getPolyvSecretKey(), playConfigReq));
        } else if (VodPlatformEnum.PRIVATE.getCode().equals(req.getVodPlatform())) {
            // 播放授权地址
            String authUrl = req.getWebsiteDomain() + "gateway/course/api/callback/priyun/auth";
            PrivateYunPlayTokenRes tokenResponse = PrivateYunVodUtil.token(req.getPriYunUrl(), authUrl, req.getPriYunAccessKeyId(), req.getPriYunAccessKeySecret(), playConfigReq.getVid(), "", playConfigReq.getExpiresIn().toString(), playConfigReq.getViewerId());
            PrivateYunVideoClarityResp playUrls = tokenResponse.getVideoClarityResp();
            if (ObjectUtil.isNotNull(playUrls)) {
                playUrls.setSdUrl(playUrls.getSdUrl() + "?token=" + tokenResponse.getToken());
                playUrls.setHdUrl(playUrls.getHdUrl() + "?token=" + tokenResponse.getToken());
            }
            return JSUtil.toJsonString(playUrls);
        }
        return "";
    }
}
