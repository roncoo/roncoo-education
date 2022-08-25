package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统配置
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
public class SysConfigPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 配置类型(1:站点信息，2:系统信息、3:其他)
     */
    private Integer configType;

    /**
     * 内容类型(1:文本、2:富文本、3图片、4布尔、5枚举)
     */
    private Integer contentType;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 配置展示(0:隐藏、1:显示)
     */
    private Boolean configShow;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序，默认值:100
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
}
