package com.roncoo.education.user.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-订单信息表
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-订单信息表查看")
public class AdminOrderInfoViewResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "订单号")
    private Long orderNo;

    @Schema(description = "下单用户编号")
    private Long userId;

    @Schema(description = "下单用户电话")
    private String mobile;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "下单用户注册时间")
    private LocalDateTime registerTime;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "划线价")
    private BigDecimal rulingPrice;

    @Schema(description = "课程价格")
    private BigDecimal coursePrice;

    @Schema(description = "支付方式：1微信支付，2支付宝支付")
    private Integer payType;

    @Schema(description = "订单状态：1待支付，2成功支付，3支付失败，4关闭支付")
    private Integer orderStatus;

    @Schema(description = "客户备注")
    private String remarkCus;

    @Schema(description = "后台备注")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "支付时间")
    private LocalDateTime payTime;
}
