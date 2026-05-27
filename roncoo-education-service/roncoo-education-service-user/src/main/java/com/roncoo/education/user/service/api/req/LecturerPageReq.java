package com.roncoo.education.user.service.api.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-讲师信息
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-讲师信息")
public class LecturerPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "讲师名称")
    private String lecturerName;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
