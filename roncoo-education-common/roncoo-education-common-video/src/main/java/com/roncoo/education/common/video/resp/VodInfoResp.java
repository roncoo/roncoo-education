package com.roncoo.education.common.video.resp;

import com.roncoo.education.common.core.enums.VideoStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询视频信息
 *
 * @author fengyw
 */
@Data
public class VodInfoResp implements Serializable {

    private static final long serialVersionUID = 2222356652726536710L;

    /**
     * 视频云平台
     */
    private Integer vodPlatform;
    /**
     * 视频id
     */
    private String vid;

    /**
     * 时长(单位:秒)
     */
    private Integer duration;

    /**
     * 封面URL
     */
    private String coverUrl;

    /**
     * 源文件大小，单位：字节
     */
    private Long fileSize;

    /**
     * 视频状态(1转码中，2成功，3失败)
     */
    private VideoStatusEnum videoStatusEnum = VideoStatusEnum.WAIT;

}
