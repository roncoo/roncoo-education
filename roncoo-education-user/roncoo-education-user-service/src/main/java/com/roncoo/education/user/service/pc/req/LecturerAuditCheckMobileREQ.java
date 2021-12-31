package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师信息-审核-校验手机号是否是否存在
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerAuditCheckMobileREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 讲师手机
     */
    @ApiModelProperty(value = "讲师手机", required = true)
    private String lecturerMobile;

}
