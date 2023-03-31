package com.roncoo.education.user.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "ADMIN-订单信息表分页")
public class AdminOrderInfoPageResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "订单号")
    private Long orderNo;

    @ApiModelProperty(value = "讲师名称")
    private String lecturerName;

    @ApiModelProperty(value = "下单用户ID")
    private Long userId;

    @ApiModelProperty(value = "下单用户电话")
    private String mobile;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "下单用户注册时间")
    private LocalDateTime registerTime;

    @ApiModelProperty(value = "讲师ID")
    private Long lecturerId;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "划线价")
    private BigDecimal rulingPrice;

    @ApiModelProperty(value = "原价")
    private BigDecimal coursePrice;

    @ApiModelProperty(value = "交易类型：1线上支付，2线下支付")
    private Integer tradeType;

    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付")
    private Integer payType;

    @ApiModelProperty(value = "订单状态：1待支付，2成功支付，3支付失败，4已关闭")
    private Integer orderStatus;

    @ApiModelProperty(value = "客户备注")
    private String remarkCus;

    @ApiModelProperty(value = "后台备注")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    private CourseViewVO courseViewVO;
}
