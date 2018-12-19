package com.roncoo.education.system.common.bean.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteNavDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 状态(1有效, 0无效)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 导航名称
     */
    private String navName;
    
    private List<WebsiteNavDTO> websiteNavList;
}
