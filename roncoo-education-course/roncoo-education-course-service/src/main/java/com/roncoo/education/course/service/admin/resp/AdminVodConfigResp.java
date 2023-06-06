package com.roncoo.education.course.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "Admin-上传音视频配置")
public class AdminVodConfigResp {

    @ApiModelProperty(value = "使用平台")
    private Integer vodPlatform;

    @ApiModelProperty(value = "视频云上传配置")
    private String vodUploadConfig;
}
