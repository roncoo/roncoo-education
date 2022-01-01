package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 后台用户信息-删除
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysUserDeleteREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

}
