package com.roncoo.education.course.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-课程视频信息
 * </p>
 *
 * @author wujing
 * @date 2022-09-02
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-课程视频信息")
public class AuthResourceReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源类型(1:视频2:音频;3:文档)")
    private Integer resourceType;

    @ApiModelProperty(value = "资源大小")
    private Long resourceSize;

    @ApiModelProperty(value = "资源地址")
    private String resourceUrl;

    @ApiModelProperty(value = "状态(1转码中，2成功，3失败)")
    private Integer videoStatus;

    @ApiModelProperty(value = "时长")
    private String videoLength;

    @ApiModelProperty(value = "vid")
    private String videoVid;
}
