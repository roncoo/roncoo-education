package com.roncoo.education.system.service.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站内信用户记录表
 *
 * @author LHR
 */
@Data
@Accessors(chain = true)
public class MsgUserBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 状态(1有效, 0无效)
	 */
	@ApiModelProperty(value = "状态(1有效, 0无效)")
	private Integer statusId;
	
	/**
	 * 机构号
	 */
	@ApiModelProperty(value = "机构号", required = true)
	private String orgNo;
	
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private Long userNo;
	
	/**
	 * 是否阅读(1是;0否)
	 */
	@ApiModelProperty(value = "是否已阅读(1是;0否)")
	private Integer isRead;
	
	 /**
     * 是否置顶(1是;0否)
     */
	@ApiModelProperty(value = "是否置顶(1是;0否)")
    private Integer isTop;

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页")
	private Integer pageCurrent = 1;
	/**
	 * 每页条数
	 */
	@ApiModelProperty(value = "每页条数")
	private Integer pageSize = 20;
}
