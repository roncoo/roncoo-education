package com.roncoo.education.course.service.biz.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-讲师信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-讲师信息查看")
public class CourseLecturerResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "讲师名称")
    private String lecturerName;

    @ApiModelProperty(value = "讲师手机")
    private String lecturerMobile;

    @ApiModelProperty(value = "讲师职位")
    private String lecturerPosition;

    @ApiModelProperty(value = "讲师头像")
    private String lecturerHead;

    @ApiModelProperty(value = "简介")
    private String introduce;
}
