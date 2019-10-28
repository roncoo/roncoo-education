package com.roncoo.education.system.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteNavArticleBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 导航ID
     */
    @ApiModelProperty(value = "导航ID")
    private Long navId;
}
