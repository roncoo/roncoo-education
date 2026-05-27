package com.roncoo.education.course.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-资源学习记录
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-资源学习记录分页")
public class AdminUserStudyPeriodPageResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "课时ID")
    private Long id;

    @Schema(description = "课时名称")
    private String periodName;

    @Schema(description = "进度(百分比)")
    private BigDecimal progress;
}
