/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    LOCAL(0, "本地"), ALIYUN(1, "阿里云"), FDSF(2, "FastDFS"), MINIO(3, "MinIO");

    private Integer code;

    private String desc;

}
