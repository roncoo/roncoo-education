package com.roncoo.education.user.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthLecturerAuditViewBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师用户编号", required = true)
    private Long lecturerUserNo;
}
