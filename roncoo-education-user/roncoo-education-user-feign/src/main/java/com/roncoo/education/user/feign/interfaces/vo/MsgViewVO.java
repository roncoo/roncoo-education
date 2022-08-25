package com.roncoo.education.user.feign.interfaces.vo;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站内信息表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
public class MsgViewVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 状态(1有效, 0无效)
     */
    private Integer statusId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 短信类型(1系统消息,2其他)
     */
    private Integer msgType;

    /**
     * 短信标题
     */
    private String msgTitle;

    /**
     * 短信内容
     */
    private String msgText;

    /**
     * 是否定时发送（1是，0否）
     */
    private Integer isTimeSend;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 是否已发送(1是;0否)
     */
    private Integer isSend;

    /**
     * 是否置顶(1是;0否)
     */
    private Integer isTop;

    /**
     * 备注
     */
    private String remark;
}
