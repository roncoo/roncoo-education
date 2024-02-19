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
@ApiModel(description = "Admin-预览配置")
public class AdminPreviewResp {

    @ApiModelProperty(value = "资源ID")
    private Long id;

    @ApiModelProperty(value = "播放vid")
    private String vid;

    @ApiModelProperty(value = "点播平台")
    private Integer vodPlatform;

    @ApiModelProperty(value = "点播播放配置参数")
    private String vodPlayConfig;

    @ApiModelProperty(value = "文档学习配置参数")
    private String docStudyConfig;
}
