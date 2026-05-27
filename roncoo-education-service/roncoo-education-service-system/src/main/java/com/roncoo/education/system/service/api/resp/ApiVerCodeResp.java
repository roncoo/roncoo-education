package com.roncoo.education.system.service.api.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 验证码
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "验证码")
public class ApiVerCodeResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "verToken")
    private String verToken;

    @Schema(description = "验证码图片")
    private String img;
}
