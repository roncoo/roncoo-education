package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-直播信息
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-直播开播信息")
public class AdminLiveBroadcastReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "课程ID")
    private Long courseId;
}
