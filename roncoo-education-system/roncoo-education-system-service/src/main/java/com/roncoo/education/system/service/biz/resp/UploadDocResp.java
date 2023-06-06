package com.roncoo.education.system.service.biz.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文档
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UploadDocResp implements Serializable {

    private static final long serialVersionUID = 6388549853399820582L;

    @ApiModelProperty(value = "存储平台")
    private Integer storagePlatform;

    @ApiModelProperty(value = "文件Url")
    private String docUrl;

    @ApiModelProperty("页数")
    private Integer pageCount;
}
