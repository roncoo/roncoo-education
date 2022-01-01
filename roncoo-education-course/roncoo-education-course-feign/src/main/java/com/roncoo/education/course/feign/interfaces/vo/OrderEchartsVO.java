package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderEchartsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总收入
     */
    private List<BigDecimal> countPaidPrice;
    private List<Integer> countPaidPriceq;

    /**
     * 总订单数
     */
    private List<Integer> countOrders;

}
