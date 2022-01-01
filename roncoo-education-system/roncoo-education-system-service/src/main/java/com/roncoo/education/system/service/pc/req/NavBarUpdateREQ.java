package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 头部导航-更新
 */
@Data
@Accessors(chain = true)
public class NavBarUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    /**
     * 状态(1有效, 0无效)
     */
    @ApiModelProperty(value = "状态(1有效, 0无效)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
    /**
     * 导航标题
     */
    @ApiModelProperty(value = "导航标题", required = false)
    private String navTitle;
    /**
     * 跳转方式
     */
    @ApiModelProperty(value = "跳转方式", required = false)
    private String target;

}
