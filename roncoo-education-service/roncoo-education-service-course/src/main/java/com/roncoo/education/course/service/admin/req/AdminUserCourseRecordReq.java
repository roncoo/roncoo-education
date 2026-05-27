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
public class AdminUserCourseRecordReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "购买类型(1支付，2免费)")
    private Integer buyType;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
