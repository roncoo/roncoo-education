package com.roncoo.education.course.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoForChartsBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师用户编号", required = true)
    private Long lecturerUserNo;
    /**
	 * 开始时间
	 */
    @ApiModelProperty(value = "开始时间", required = false)
	private String beginCreate;
	/**
	 * 结束时间
	 */
    @ApiModelProperty(value = "结束时间", required = false)
	private String endCreate;
}
