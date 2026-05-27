package com.roncoo.education.course.service.biz.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * API-章节信息
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-章节信息")
public class CourseChapterResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "章节名称")
    private String chapterName;

    @Schema(description = "章节描述")
    private String chapterDesc;

    @Schema(description = "是否免费(1免费，0收费)")
    private Integer isFree;

    @Schema(description = "课时信息")
    private List<CourseChapterPeriodResp> periodRespList;
}
