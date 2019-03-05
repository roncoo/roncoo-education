package com.roncoo.education.system.common.bean.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站内信息表，发送用
 *
 * @author LHR
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
     * 
     */
    private Integer isTop;
}
