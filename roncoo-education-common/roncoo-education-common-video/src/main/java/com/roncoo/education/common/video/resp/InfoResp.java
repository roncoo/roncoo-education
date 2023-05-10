package com.roncoo.education.common.video.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 查询账号信息
 *
 * @author fengyw
 */
@Data
public class InfoResp implements Serializable {

    private static final long serialVersionUID = 2222356652726536710L;

    /**
     * 账号
     */
    private String uniqueNo;

    /**
     * 总空间，单位：GB
     */
    private BigDecimal totalSpace = BigDecimal.ZERO;
    /**
     * 使用空间，单位：GB
     */
    private BigDecimal usedSpace = BigDecimal.ZERO;
    /**
     * 总流量，单位：GB
     */
    private BigDecimal totalFlow = BigDecimal.ZERO;
    /**
     * 使用流量，单位：GB
     */
    private BigDecimal usedFlow = BigDecimal.ZERO;

}
