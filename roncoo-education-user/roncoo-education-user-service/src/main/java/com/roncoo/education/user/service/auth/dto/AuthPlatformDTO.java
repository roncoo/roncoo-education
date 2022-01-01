package com.roncoo.education.user.service.auth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthPlatformDTO implements Serializable {

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
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 机构号
     */
    private String orgNo;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 客户端密匙
     */
    private String clientSecret;
    /**
     * 客户端名称
     */
    private String clientName;
    /**
     * 角色类型
     */
    private Integer roleType;
    /**
     * 备注
     */
    private String remark;
}
