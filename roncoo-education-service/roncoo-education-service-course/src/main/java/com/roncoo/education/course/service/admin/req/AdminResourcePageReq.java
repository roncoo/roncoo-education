package com.roncoo.education.course.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-课程视频信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-课程视频信息分页")
public class AdminResourcePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源类型(1:视频2:音频;3:文档)")
    private Integer resourceType;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
