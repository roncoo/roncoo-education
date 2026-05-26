package com.roncoo.education.system.service.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * AI写作-创建任务请求
 *
 * @author roncoo
 */
@Data
public class AdminAiWriteCreateReq {

    @NotNull(message = "写作类型不能为空")
    @ApiModelProperty(value = "写作类型(1:通用写作 2:课程简介 3:文章撰写 4:营销文案 5:教学大纲)", required = true)
    private Integer writeType;

    @NotBlank(message = "创作内容不能为空")
    @ApiModelProperty(value = "用户输入的创作提示词", required = true)
    private String userPrompt;
}
