package com.roncoo.education.common.video.impl.polyv.live.response;

import lombok.Data;

import java.util.List;

/**
 * @author LYQ
 */
@Data
public class CoursewareRefashionTaskResponse {

    /**
     * 当前页数
     */
    private Integer pageNumber;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 每页数据大小
     */
    private Integer pageSize;

    /**
     * 任务列表
     */
    private List<Content> contents;

    /**
     * 任务内容
     */
    @Data
    public static class Content {

        /**
         * 任务id
         */
        private String taskId;

        /**
         * 频道号
         */
        private String channelId;

        /**
         * 对应回放的名称
         */
        private String title;

        /**
         * 重制mp4下载地址，有24小时的防盗链超时时间
         */
        private String url;

        /**
         * 重制课件模块中的场次id
         */
        private String sessionId;

        /**
         * 对应回放的直播开始时间，格式为yyyyMMddhhmmss
         */
        private String startTime;

        /**
         * 状态值
         * waiting：等待处理
         * process：处理中
         * success：重制成功
         * fail：重制失败
         * uploaded：上传点播成功
         * uploadFailed：上传点播失败
         */
        private String status;

        /**
         * 重制剩余的过期时间，过期后将无法访问和下载
         */
        private Long remainDay;

        /**
         * 重制的视频时长，单位秒
         */
        private Long duration;

        /**
         * 视频ID
         */
        private Long videoId;
    }
}
