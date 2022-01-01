package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单信息分页列表
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoListBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单状态1待支付，2成功支付，3支付失败，4已关闭
     */
    @ApiModelProperty(value = "订单状态(1待支付，2成功支付，3支付失败，4已关闭)")
    private Integer orderStatus;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号（用户订单用到）")
    private Long userNo;

    /**
     * 讲师编号
     */
    @ApiModelProperty(value = "讲师编号（讲师收益用到）")
    private Long lecturerUserNo;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
