package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 后台用户信息-修改
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysUserUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Schema(description = "主键", required = true)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 手机
     */
    @Schema(description = "手机", required = true)
    private String mobile;
    /**
     * 排序
     */
    @Schema(description = "排序", required = false)
    private Integer sort;
    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名", required = true)
    private String realName;
    /**
     * 备注
     */
    @Schema(description = "备注", required = false)
    private String remark;

}
