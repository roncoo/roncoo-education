package com.roncoo.education.system.service.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteLinkListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 站点友情链接集合
     */
    @ApiModelProperty(value = "站点友情链接集合")
    private List<WebsiteLinkDTO> WebsiteLinkList = new ArrayList<>();

}
