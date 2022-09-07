package com.roncoo.education.common.polyv.vod.response;

import lombok.Data;

import java.util.List;

/**
 * 分类查看响应对象
 *
 * @author LYQ
 */
@Data
public class CategoryViewResponse {

    /**
     * 分类名和该分类下视频总数的组合
     */
    private String text;

    /**
     * 分类名
     */
    private String cataname;

    /**
     * 分类树，用逗号拼接从根目录到该目录每一层的分类id，例如："1,1617160372987"
     */
    private String catatree;

    /**
     * 分类id，如果为1则是根目录
     */
    private Long cataid;

    /**
     * 上一级分类id，根目录的上一级分类为0
     */
    private Long parentid;

    /**
     * 一级分类设置信息，分别为：加密、水印、课件优化、源文件播放。例如：加密
     */
    private String cataProfile;

    /**
     * 此分类及其子分类视频总数
     */
    private Integer videos;

    /**
     * 该分类的子分类列表，没有子分类时此字段无效
     */
    private List<CategoryViewResponse> nodes;
}
