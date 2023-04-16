package com.roncoo.education.course.service.biz.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程评论
 * </p>
 *
 * @author wujing
 * @date 2023-03-24
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "课程评论")
public class CourseCommentResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户信息")
    private UsersVO usersVO;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "评论ID")
    private Long commentId;

    @ApiModelProperty(value = "评论内容")
    private String commentText;

    @ApiModelProperty(value = "课程评论")
    private List<CourseCommentResp> courseCommentRespList;
}
