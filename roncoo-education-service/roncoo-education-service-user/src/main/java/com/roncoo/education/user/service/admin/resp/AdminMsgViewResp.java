package com.roncoo.education.user.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-站内信息表
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-站内信息表查看")
public class AdminMsgViewResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "状态(1有效, 0无效)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "短信类型(1系统消息,2其他)")
    private Integer msgType;

    @Schema(description = "短信标题")
    private String msgTitle;

    @Schema(description = "短信内容")
    private String msgText;

    @Schema(description = "是否定时发送（1是，0否）")
    private Integer isTimeSend;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "发送时间")
    private LocalDateTime sendTime;

    @Schema(description = "是否已发送(1是;0否)")
    private Integer isSend;

    @Schema(description = "是否置顶(1是;0否)")
    private Integer isTop;

    @Schema(description = "备注")
    private String remark;
}
