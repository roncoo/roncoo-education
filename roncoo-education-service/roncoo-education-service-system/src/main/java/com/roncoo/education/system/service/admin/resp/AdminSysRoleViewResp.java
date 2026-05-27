package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色信息-查看
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysRoleViewResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String roleName;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
