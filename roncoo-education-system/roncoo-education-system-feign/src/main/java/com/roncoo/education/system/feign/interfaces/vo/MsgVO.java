package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 状态(1有效, 0无效)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 短信类型
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
    private Date sendTime;
    /**
     * 是否已发送(1是;0否)
     */
    private Integer isSend;
    /**
     * 是否置顶(1是;0否)
     */
    private Integer isTop;
    /**
     * 后台备注
     */
    private String backRemark;

}
