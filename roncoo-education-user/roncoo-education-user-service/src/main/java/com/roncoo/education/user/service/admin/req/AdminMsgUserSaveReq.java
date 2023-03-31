package com.roncoo.education.user.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-站内信用户记录表
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-站内信用户记录表添加")
public class AdminMsgUserSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态(1有效, 0无效)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "短信ID")
    private Long msgId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "短信类型(1系统消息,2其他)")
    private Integer msgType;

    @ApiModelProperty(value = "短信标题")
    private String msgTitle;

    @ApiModelProperty(value = "是否阅读(1是;0否)")
    private Integer isRead;

    @ApiModelProperty(value = "是否置顶(1是;0否)")
    private Integer isTop;
}
