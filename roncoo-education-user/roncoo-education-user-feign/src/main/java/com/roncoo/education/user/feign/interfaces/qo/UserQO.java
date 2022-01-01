package com.roncoo.education.user.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class UserQO implements Serializable {

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
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;
    /**
     * 用户编号
     */
    private Long userNo;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 密码盐
     */
    private String mobileSalt;
    /**
     * 登录密码
     */
    private String mobilePsw;
    /**
     * 用户来源(client_id)
     */
    private String userSource;
}
