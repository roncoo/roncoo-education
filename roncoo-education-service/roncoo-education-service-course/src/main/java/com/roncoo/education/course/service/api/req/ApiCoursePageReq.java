package com.roncoo.education.course.service.api.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-课程信息
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-课程信息")
public class ApiCoursePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "课程名称")
    private String courseName;

    @Schema(description = "是否免费(1:免费，0:收费)")
    private Integer isFree;

    /**
     * 当前页
     */
    @Schema(description = "当前页")
    private Integer pageCurrent = 1;
    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Integer pageSize = 20;
}
