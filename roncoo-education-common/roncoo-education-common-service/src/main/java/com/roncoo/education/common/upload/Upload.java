package com.roncoo.education.common.upload;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 交易下单通知
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class Upload implements Serializable {

    private static final long serialVersionUID = 1195869049655301491L;

    /**
     * 存储平台，参考：UploadModeEnum
     */
    private String storagePlatform;

    /**
     * 站点域名
     */
    private String websiteDomain;

    /**
     * MinIO
     */
    private String minioDomain;
    private String minioBucket;
}
