package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站内信息表，发送用
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgPushVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 标题
     */
    private String msgTitle;

    /**
     * 是否置顶
     */
    private Integer isTop;
}
