package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteLinkVO implements Serializable {

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
     * 名称
     */
    private String linkName;
    /**
     * 链接
     */
    private String linkUrl;
    /**
     * 跳转方式(_blank，_self)
     */
    private String linkTarget;

}
