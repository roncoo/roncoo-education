package com.roncoo.education.system.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-app版本管理
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-app版本管理分页")
public class AdminWebsiteAppPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "app类型(1:Android，2:IOS)")
    private Integer appType;

    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
