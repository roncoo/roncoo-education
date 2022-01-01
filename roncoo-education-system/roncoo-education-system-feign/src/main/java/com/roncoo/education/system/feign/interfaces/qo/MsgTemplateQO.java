package com.roncoo.education.system.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息模板
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgTemplateQO implements Serializable {

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
     * 标题
     */
    private String title;
    /**
     * 备注
     */
    private String remark;
}
