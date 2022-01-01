package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 底部导航-更新
 */
@Data
@Accessors(chain = true)
public class WebsiteNavUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
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
     * 父ID
     */
    @ApiModelProperty(value = "父ID", required = false)
    private Long parentId;
    /**
     * 导航名称
     */
    @ApiModelProperty(value = "导航名称", required = true)
    private String navName;

}
