package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "ADMIN-课程视频信息删除")
public class AdminResourceDeleteReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键集合")
    private List<Long> ids;

}
