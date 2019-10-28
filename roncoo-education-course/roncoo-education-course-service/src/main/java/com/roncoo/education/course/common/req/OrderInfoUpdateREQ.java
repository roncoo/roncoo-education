package com.roncoo.education.course.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单信息表-更新
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class OrderInfoUpdateREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	/**
	 * 后台备注
	 */
	@ApiModelProperty(value = "后台备注", required = false)
	private String remark;

}
