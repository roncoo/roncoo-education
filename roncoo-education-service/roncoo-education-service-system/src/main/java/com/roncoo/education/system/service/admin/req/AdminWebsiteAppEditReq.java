package com.roncoo.education.system.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-app版本管理
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-app版本管理修改")
public class AdminWebsiteAppEditReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "状态(1有效, 0无效)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime publishTime;

    @ApiModelProperty(value = "app类型(1:Android，2:IOS)")
    private Integer appType;

    @ApiModelProperty(value = "app版本")
    private String appVersion;

    @ApiModelProperty(value = "是否强制更新(1不强制，2强制)")
    private Integer updateForce;

    @ApiModelProperty(value = "app下载地址")
    private String updateUrl;

    @ApiModelProperty(value = "更新内容")
    private String updateTips;
}
