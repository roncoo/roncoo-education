package com.roncoo.education.user.service.admin.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户日志
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "用户登录")
public class AdminStatLoginResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "时间")
    private List<String> dateList;

    @Schema(description = "当天登录总次数")
    private List<Long> loginList;

    @Schema(description = "当天注册总次数")
    private List<Long> registerList;

}
