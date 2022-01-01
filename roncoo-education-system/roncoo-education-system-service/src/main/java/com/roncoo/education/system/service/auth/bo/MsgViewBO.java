package com.roncoo.education.system.service.auth.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgViewBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

}
