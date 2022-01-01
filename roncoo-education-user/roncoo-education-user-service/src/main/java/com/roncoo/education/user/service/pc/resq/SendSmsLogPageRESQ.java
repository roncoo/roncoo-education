package com.roncoo.education.user.service.pc.resq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class SendSmsLogPageRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int pageCurrent;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private int pageSize;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 短信模板
     */
    @ApiModelProperty(value = "短信模板")
    private String template;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String smsCode;
    /**
     * 是否发送成功(1发送成功，0:发送失败)
     */
    @ApiModelProperty(value = "是否发送成功(1发送成功，0:发送失败)")
    private Integer isSuccess;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String beginGmtCreate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endGmtCreate;
}
