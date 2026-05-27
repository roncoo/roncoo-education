package com.roncoo.education.system.service.biz.resp;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "存储平台")
    private Integer storagePlatform;

    @Schema(description = "文件Url")
    private String docUrl;

    @Schema(description = "页数")
    private Integer pageCount;
}
