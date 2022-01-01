package com.roncoo.education.system.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
