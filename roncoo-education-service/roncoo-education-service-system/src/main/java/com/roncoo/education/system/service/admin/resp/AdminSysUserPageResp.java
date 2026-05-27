package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 后台用户信息-分页
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysUserPageResp implements Serializable {

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
     * 手机
     */
    @Schema(description = "手机")
    private String mobile;
    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "角色名称集合")
    private List<String> roleNameList;
}
