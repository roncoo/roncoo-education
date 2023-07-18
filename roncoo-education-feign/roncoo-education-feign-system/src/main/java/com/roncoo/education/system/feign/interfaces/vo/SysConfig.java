package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统配置
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 网关域名
     */
    private String websiteDomain;

    /**
     * 存储平台
     */
    private Integer storagePlatform;

}
