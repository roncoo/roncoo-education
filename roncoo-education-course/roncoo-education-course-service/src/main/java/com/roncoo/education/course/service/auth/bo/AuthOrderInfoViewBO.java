package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoViewBO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号 ", required = true)
    private Long orderNo;
}
