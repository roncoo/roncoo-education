package com.roncoo.education.system.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信用户记录表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgUserQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int pageCurrent;
    /**
     * 每页记录数
     */
    private int pageSize;
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
     * 短信ID
     */
    private Long msgId;
    /**
     * 用户编号
     */
    private Long userNo;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 短信类型
     */
    private Integer msgType;
    /**
     * 短信标题
     */
    private String msgTitle;
    /**
     * 是否阅读(1是;0否)
     */
    private Integer isRead;
    /**
     * 是否置顶(1是;0否)
     */
    private Integer isTop;
}
