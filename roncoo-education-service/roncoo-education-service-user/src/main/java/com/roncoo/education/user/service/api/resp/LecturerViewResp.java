package com.roncoo.education.user.service.api.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * API-讲师信息
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-讲师信息查看")
public class LecturerViewResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "讲师名称")
    private String lecturerName;

    @Schema(description = "讲师手机")
    private String lecturerMobile;

    @Schema(description = "讲师职位")
    private String lecturerPosition;

    @Schema(description = "讲师头像")
    private String lecturerHead;

    @Schema(description = "简介")
    private String introduce;

    @Schema(description = "课程集合")
    private List<CourseViewVO> courseViewVO;
}
