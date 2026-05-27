package com.roncoo.education.course.service.api.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * API-专区课程关联表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-专区课程关联表")
public class ApiZoneCourseResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
    private Long id;

    @Schema(description = "课程名称")
    private String courseName;

    @Schema(description = "课程封面")
    private String courseLogo;

    @Schema(description = "划线价")
    private BigDecimal rulingPrice;

    @Schema(description = "课程价")
    private BigDecimal coursePrice;

    @Schema(description = "购买人数")
    private Integer countBuy;

    @Schema(description = "学习人数")
    private Integer countStudy;
}
