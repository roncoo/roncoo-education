package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteNavVO implements Serializable {

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
     * 父ID
     */
    private Long parentId;
    /**
     * 导航名称
     */
    private String navName;
    /**
     * 是否存在文章
     */
    private Integer isArticle;

    private List<WebsiteNavVO> websiteNavList;
}
