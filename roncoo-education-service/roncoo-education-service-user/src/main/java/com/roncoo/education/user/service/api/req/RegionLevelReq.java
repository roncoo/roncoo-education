/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.service.api.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class RegionLevelReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别", required = true)
    private Integer level;

}
