package com.roncoo.education.user.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 讲师信息分页信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerPageRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 讲师编号
     */
    @ApiModelProperty(value = "讲师编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lecturerUserNo;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称")
    private String lecturerName;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String lecturerMobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String lecturerEmail;
    /**
     * 讲师分成比例
     */
    @ApiModelProperty(value = "讲师分成比例")
    private BigDecimal lecturerProportion;

}
