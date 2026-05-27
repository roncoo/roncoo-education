package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-课程用户关联表
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-课程用户关联表分页")
public class AdminUserCoursePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "购买类型(1支付，2免费)")
    private Integer buyType;

    @Schema(description = "课程名称")
    private String courseName;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
