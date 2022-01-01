package com.roncoo.education.user.feign.interfaces.qo;

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
public class PlatformQO implements Serializable {

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
     * 排序
     */
    private Integer sort;
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
     * 备注
     */
    private String remark;
}
