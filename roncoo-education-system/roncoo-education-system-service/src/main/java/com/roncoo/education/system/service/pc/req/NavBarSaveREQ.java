package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 头部导航-保存
 */
@Data
@Accessors(chain = true)
public class NavBarSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 导航标题
     */
    @ApiModelProperty(value = "导航标题", required = true)
    private String navTitle;
    /**
     * 跳转方式
     */
    @ApiModelProperty(value = "跳转方式", required = true)
    private String target;
    /**
     * 导航url
     */
    @ApiModelProperty(value = "导航url", required = true)
    private String navUrl;

}
