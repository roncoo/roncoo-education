/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 收入统计
 *
 * @author forest
 */
@Data
@Accessors(chain = true)
public class CountIncomeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总收入
     */
    private BigDecimal totalProfit = BigDecimal.valueOf(0);

    /**
     * 平台收入
     */
    private BigDecimal platformProfit = BigDecimal.valueOf(0);

    /**
     * 讲师收入
     */
    private BigDecimal lecturerProfit = BigDecimal.valueOf(0);

}
