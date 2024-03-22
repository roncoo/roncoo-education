package com.roncoo.education.course.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * ADMIN-课程视频信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-课程视频信息删除")
public class AdminResourceBatchEditReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键集合")
    private List<Long> ids;

    @ApiModelProperty(value = "分类ID")
    private Long categoryId;

}
