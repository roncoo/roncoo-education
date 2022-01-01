package com.roncoo.education.user.service.auth.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户修改日志
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthUserLogModifiedBO implements Serializable {

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
     * 用户编号
     */
    private Long userNo;
    /**
     * 原手机号
     */
    private String mobileOld;
    /**
     * 新手机
     */
    private String mobileNew;
    /**
     * 备注
     */
    private String remark;
}
