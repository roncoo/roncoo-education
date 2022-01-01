/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author wujing123
 */
@Data
@Accessors(chain = true)
public class UserAuthBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid", required = true)
    private String uuid;

}
