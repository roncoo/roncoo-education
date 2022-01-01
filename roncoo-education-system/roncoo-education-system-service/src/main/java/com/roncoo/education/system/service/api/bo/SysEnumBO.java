package com.roncoo.education.system.service.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysEnumBO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 枚举名称
     */
    private String enumName;
}
