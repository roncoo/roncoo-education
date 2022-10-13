package com.roncoo.education.common.polyv.vod.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 异步批量上传视频
 *
 * @author LYQ
 */
@Data
public class VideoInfoAsyncBatchUploadRequest implements Serializable {

    /**
     * 上传视频的分类id - 非必填
     */
    private String cataid;

    /**
     * 是否录屏优化，默认为0：不优化 - 非必填
     */
    private Integer luping;

    /**
     * 自定义水印图片链接，图片格式必须是png格式，支持http、https协议 - 非必填
     */
    private String watermark;

    /**
     * 自定义水印图片位置，如没该参数，则自定义水印的显示情况跟随分类或账号设置 - 非必填
     * 1：左上角
     * 2：右上角
     * 3：左下角
     * 4：右下角
     */
    private String watermarkLocation;

    /**
     * 上传视频信息json数组，最多100个 - 必填
     */
    private List<UploadInfo> uploadInfos;

    /**
     * 上传视频信息
     */
    @Data
    public static class UploadInfo implements Serializable {

        /**
         * 待上传视频的URL，支持http、https协议，长度限制：1000 个字符 - 必填
         */
        private String fileUrl;

        /**
         * 视频标题，长度限制：100 个字符 - 必填
         */
        private String title;

        /**
         * 自定义数据，长度限制：100 个字符，如果提交了该字段，会在上传完成回调时透传返回 - 非必填
         */
        private String state;
    }
}
