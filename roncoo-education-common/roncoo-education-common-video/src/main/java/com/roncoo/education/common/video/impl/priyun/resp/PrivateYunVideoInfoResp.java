package com.roncoo.education.common.video.impl.priyun.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询视频信息
 *
 * @author LYQ
 */
@Data
public class PrivateYunVideoInfoResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频vid
     */
    private String videoVid;
    /**
     * 视频缩略图地址
     */
    private String videoThumbnail;
    /**
     * 视频分辨率
     */
    private String videoDefinition;

    /**
     * 视频时长
     */
    private String videoDuration;
    /**
     * 视频MD5
     */
    private String videoMd5;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频码率
     */
    private String videoRate;
    /**
     * 视频大小
     */
    private String videoSize;
    /**
     * 视频状态(1:上传中;2:上传失败;3:转码中;4:转码失败;5:转码完成)
     */
    private String videoStatus;
}
