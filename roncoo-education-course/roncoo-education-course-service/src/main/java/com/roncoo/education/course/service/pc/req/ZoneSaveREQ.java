package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 专区-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZoneSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 专区名称
     */
    @ApiModelProperty(value = "专区名称", required = true)
    private String zoneName;
    /**
     * 专区描述
     */
    @ApiModelProperty(value = "专区描述", required = true)
    private String zoneDesc;
}
