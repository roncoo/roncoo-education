package com.roncoo.education.common.polyv.vod.response;

import lombok.Data;

/**
 * 创建分类响应对象
 *
 * @author LYQ
 */
@Data
public class CategoryAddResponse {

    /**
     * 新建的分类目录id
     */
    private Long cataid;

    /**
     * 新建的分类目录树，逗号分割（状态为半角），例如 1b8be3,239c2e
     */
    private String catatree;

}
