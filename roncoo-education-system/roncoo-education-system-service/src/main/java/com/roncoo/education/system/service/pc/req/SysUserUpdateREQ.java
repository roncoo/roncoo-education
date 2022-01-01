package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 后台用户信息-更新
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysUserUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机", required = true)
    private String mobile;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名", required = true)
    private String realName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    private String remark;

}
