package com.roncoo.education.course.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "AdminVodConfigResp", description = "Admin-上传音视频配置")
public class AdminVodConfigResp {

    @ApiModelProperty(value = "使用平台")
    private Integer vodPlatform;

    @ApiModelProperty(value = "配置")
    private String config;

    /**
     * 保利威
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PolyvConfig {

        @ApiModelProperty(value = "账号ID")
        private String userid;

        @ApiModelProperty(value = "时间戳")
        private Long ptime;

        @ApiModelProperty(value = "sign")
        private String sign;

        @ApiModelProperty(value = "hash")
        private String hash;

        @ApiModelProperty(value = "点播分类ID")
        private Long cataid = 1L;
    }

    /**
     * 私有化
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PriConfig {
        // TODO
    }
}
