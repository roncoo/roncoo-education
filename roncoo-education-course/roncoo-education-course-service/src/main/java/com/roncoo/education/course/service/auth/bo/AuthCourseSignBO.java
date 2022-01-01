/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 获取课时code值实体类
 *
 * @author forest
 */
@Data
@Accessors(chain = true)
public class AuthCourseSignBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;
    /**
     * 课时编号
     */
    @ApiModelProperty(value = "课时编号", required = true)
    private Long periodId;
    /**
     * 课时vid
     */
    @ApiModelProperty(value = "视频VID", required = true)
    private String videoVid;
    /**
     * 播放ip
     */
    @ApiModelProperty(value = "播放IP地址", required = false)
    private String ip = "127.0.0.1";

}
