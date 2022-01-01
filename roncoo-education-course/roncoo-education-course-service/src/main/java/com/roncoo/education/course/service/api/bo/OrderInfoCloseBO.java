package com.roncoo.education.course.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 关闭订单信息
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class OrderInfoCloseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号", required = true)
    private Long orderNo; // 订单编号

}
