package com.roncoo.education.system.service.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * AI写作-流式获取内容请求
 *
 * @author roncoo
 */
@Data
public class AdminAiWriteCompletionsReq {

    @NotBlank(message = "aiToken不能为空")
    @ApiModelProperty(value = "AI任务Token", required = true)
    private String aiToken;
}
