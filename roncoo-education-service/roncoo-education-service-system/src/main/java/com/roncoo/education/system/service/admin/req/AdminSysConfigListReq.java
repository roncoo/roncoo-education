package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-系统配置
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-系统配置添加")
public class AdminSysConfigListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "配置类型(1:站点信息，2:系统信息、3:其他)")
    private Integer configType;

    @Schema(description = "内容类型(1:文本、2:富文本、3图片、4布尔、5枚举)")
    private Integer contentType;

    @Schema(description = "参数名称")
    private String configName;

    @Schema(description = "参数键名")
    private String configKey;

    @Schema(description = "参数键值")
    private String configValue;

    @Schema(description = "配置展示(0:隐藏、1:显示)")
    private Boolean configShow;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序，默认值:100")
    private Integer sort;
}
