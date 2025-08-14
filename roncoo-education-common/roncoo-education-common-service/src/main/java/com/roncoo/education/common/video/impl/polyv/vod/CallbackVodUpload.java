package com.roncoo.education.common.video.impl.polyv.vod;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class CallbackVodUpload {

    /**
     * 回调类型
     */
    private String type;

    /**
     * 用户自定义数据，在上传远程视频接口中，如果提交了该字段，会在事件完成回调时透传返回
     */
    private String state;

    /**
     * 系统签名
     */
    private String sign;

    private String vid;

    /**
     * 用户提交的需要远程上传的视频URL地址--异步上传处理失败
     */
    private String fileUrl;

    /**
     * 用户提交的需要远程上传的视频标题--异步上传处理失败
     */
    private String title;

    /**
     * 转码后的视频格式，非加密视频默认转码出flv、mp4格式；加密视频默认转码出pdx、m3u8格式--视频单个清晰度转码完成
     */
    private String format;

    /**
     * 视频清晰度：1 流畅，2 高清，3 超清--视频单个清晰度转码完成
     */
    private String df;

    /**
     * 事件类型
     */
    private String eventType;

    /**
     * 视频全部清晰度转码完成
     */
    private List<VideoInfo> videoInfos;

    /**
     * 操作时间,格式：yyyy-MM-dd HH:mm:ss--视频删除完成
     */
    private String operateTime;

    private String operator;

    /**
     * 操作状态
     * 视频删除完成--success 成功
     * 课件转换完成--success 转换成功， convertFailed 转换失败，auditNotPassed 内容审核不通过
     */
    private String status;

    /**
     * 删除方式：MoveToTrash 删除到回收站 DeletePermanently 永久删除--视频删除完成
     */
    private String deleteType;

    /**
     * 被删除视频的vid，批量删除时返回多个vid,英文状态逗号分隔--视频删除完成
     */
    private String vids;

    /**
     * 扫描来源，video 视频 ppt 三分屏课件--视频内容安全审核不通过
     */
    private String scanSource;

    /**
     * 扫描结果数组，包含一个或多个扫描结果
     */
    private List<ScanResult> scanResults;

    /**
     * 转换后的图片，包含一个或多个图片信息，当转换失败时不返回
     */
    private List<ConvertedImage> convertedImages;

    @Data
    public static class VideoInfo {

        /**
         * 单个清晰度视频转码状态，取值：success 成功，fail 失败
         */
        private String status;

        /**
         * 视频格式，如：mp4
         */
        private String format;

        /**
         * 视频清晰度：1 流畅，2 高清，3 超清
         */
        private String df;

        /**
         * 视频码率
         */
        private Integer bitrate;

        /**
         * 视频时长，单位：秒
         */
        private String duration;

        /**
         * 视频大小，单位：Byte
         */
        private Integer size;

        /**
         * 视频分辨率
         */
        private String resolution;
    }

    @Data
    public static class ScanResult {

        /**
         * 检测图片的URL
         */
        private String imageUrl;

        /**
         * 检测场景：porn 鉴黄 terrorism 暴恐涉政识别
         */
        private String scene;

        /**
         * 检测结果分类，当scene=porn时，label取值为：normal:正常图片 sexy:性感图片 porn:色情图片 当scene为terrorism时，label取值为：normal:正常图片 politics:涉政图片 bloody:血腥图片 weapon:武器 others:其它
         */
        private String label;

        /**
         * 检测建议：review 需人工复审；block 确认违规
         */
        private String suggestion;

        /**
         * 检测结果为该分类的概率，取值范围[0.00-100.00]
         */
        private Float rate;
    }

    @Data
    private static class ConvertedImage {

        /**
         * 课件页码，从1开始
         */
        private Integer pageNo;

        /**
         * 课件页标题，若PPT文件中未设置页标题，则值为空
         */
        private String pageTitle;

        /**
         * 转换后的图片地址
         */
        private String pageImage;

        /**
         * 缩略图地址
         */
        private String pageThumbnail;
    }
}
