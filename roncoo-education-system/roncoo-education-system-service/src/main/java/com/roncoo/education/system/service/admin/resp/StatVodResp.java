package com.roncoo.education.system.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 录播信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "StatisticsVodRESP", description = "点播信息")
public class StatVodResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户总空间，单位G")
    private BigDecimal totalSpace = BigDecimal.ZERO;

    @ApiModelProperty(value = "已用空间，单位G")
    private BigDecimal usedSpace = BigDecimal.ZERO;

    @ApiModelProperty(value = "用户总流量，单位G")
    private BigDecimal totalFlow = BigDecimal.ZERO;

    @ApiModelProperty(value = "已用流量，单位G")
    private BigDecimal usedFlow = BigDecimal.ZERO;

    private String email;

}
