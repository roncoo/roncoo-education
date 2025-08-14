package com.roncoo.education.common.video.impl.polyv.vod.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询视频信息
 *
 * @author LYQ
 */
@Data
public class PolyvVideoInfoResp implements Serializable {

    private static final long serialVersionUID = 2222356652726536710L;

    /**
     * 视频id
     */
    private String vid;

    /**
     * 视频基础信息
     */
    private BasicInfo basicInfo;

    /**
     * 视频元数据
     */
    private MetaData metaData;

    /**
     * 转码信息
     */
    private List<TranscodeInfo> transcodeInfos;

    /**
     * 截图信息
     */
    private SnapshotInfo snapshotInfo;

    /**
     * 视频基础信息
     */
    @Data
    public static class BasicInfo {

        /**
         * 视频标题
         */
        private String title;

        /**
         * 视频描述
         */
        private String description;

        /**
         * 视频时长，单位：秒
         */
        private Integer duration;

        /**
         * 首图地址，大图
         */
        private String coverURL;

        /**
         * 创建时间，格式：yyyy-MM-dd HH:mm:ss
         */
        private String creationTime;

        /**
         * 更新时间，格式：yyyy-MM-dd HH:mm:ss
         */
        private String updateTime;

        /**
         * 源文件大小，单位：字节
         */
        private Long size;

        /**
         * 视频状态码
         * 60/61：已发布
         * 10：等待编码
         * 20：正在编码
         * 40：编码失败
         * 50：等待审核
         * 51：审核不通过
         * -1：已删除
         */
        private Integer status;

        /**
         * 分类id，如1为默认分类
         */
        private Long cateId;

        /**
         * 分类名称
         */
        private String cateName;

        /**
         * 视频标签
         */
        private String tags;

        /**
         * 上传者
         */
        private String uploader;
    }

    /**
     * 视频元数据
     */
    @Data
    public static class MetaData {

        /**
         * 源文件大小，单位：字节
         */
        private Long size;

        /**
         * 视频容器类型，如mp4、flv等
         */
        private String format;

        /**
         * 源视频时长，单位：秒
         */
        private Integer duration;

        /**
         * 视频码率，单位：bps
         */
        private Integer bitrate;

        /**
         * 视频帧率
         */
        private Integer fps;

        /**
         * 分辨率高，单位：像素
         */
        private Integer height;

        /**
         * 分辨率宽，单位：像素
         */
        private Integer width;

        /**
         * 编码格式，如h264、h265等
         */
        private String codec;
    }

    /**
     * 转码信息
     */
    @Data
    public static class TranscodeInfo {

        /**
         * 播放地址
         */
        private String playUrl;

        /**
         * 清晰度
         * SOURCE：原清晰度
         * LD：普清
         * SD：标清
         * HD：高清
         */
        private String definition;

        /**
         * 视频码率，单位：kbps
         */
        private Integer bitrate;

        /**
         * 时长，单位：秒
         */
        private Integer duration;

        /**
         * 视频是否加密
         * true：加密视频
         * false：非加密
         */
        private Boolean encrypt;

        /**
         * 转码格式，如mp4、flv、pdx、hls
         */
        private String format;

        /**
         * 视频帧率
         */
        private Integer fps;

        /**
         * 分辨率高，单位：像素
         */
        private Integer height;

        /**
         * 分辨率宽，单位：像素
         */
        private Integer width;

        /**
         * 视频状态
         * normal：正常播放
         * unavailable：不能正常播放
         */
        private String status;
    }

    /**
     * 截图信息
     */
    @Data
    public static class SnapshotInfo {

        /**
         * 视频截图url
         */
        private String[] imageUrl;
    }
}
