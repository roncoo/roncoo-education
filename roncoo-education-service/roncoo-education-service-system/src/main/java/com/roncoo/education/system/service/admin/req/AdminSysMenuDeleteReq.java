package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单信息-删除
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysMenuDeleteReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID", required = true)
    private Long id;

}
