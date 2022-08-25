package com.roncoo.education.system.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 站点导航文章
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
public class WebsiteArticlePageQO implements Serializable {

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
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

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
     * 文章名称
     */
    private String artName;

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
