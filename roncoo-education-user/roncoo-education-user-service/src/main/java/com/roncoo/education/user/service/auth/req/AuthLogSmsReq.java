package com.roncoo.education.user.service.auth.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * API-AUTH-用户短信发送日志
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AuthLogSmsReq", description = "API-AUTH-用户短信发送日志")
public class AuthLogSmsReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "短信模板")
    private String template;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String smsCode;

    @ApiModelProperty(value = "是否发送成功(1发送成功，0:发送失败)")
    private Integer isSuccess;
}
