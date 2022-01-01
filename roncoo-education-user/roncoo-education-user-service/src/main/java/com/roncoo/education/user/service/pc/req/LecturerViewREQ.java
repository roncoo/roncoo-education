package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师信息查看
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerViewREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 讲师编号
     */
    @ApiModelProperty(value = "讲师编号")
    private Long lecturerUserNo;
}
