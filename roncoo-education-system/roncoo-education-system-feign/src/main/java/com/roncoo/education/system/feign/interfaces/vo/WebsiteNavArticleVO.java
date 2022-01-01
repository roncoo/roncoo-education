package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteNavArticleVO implements Serializable {

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
     * 导航ID
     */
    private Long navId;
    /**
     * 文章标题
     */
    private String artTitle;
    /**
     * 文章图片
     */
    private String artPic;
    /**
     * 文章描述
     */
    private String artDesc;

}
