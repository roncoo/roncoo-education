package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 后台用户信息-分页
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysUserPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机", required = false)
    private String mobile;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;
}
