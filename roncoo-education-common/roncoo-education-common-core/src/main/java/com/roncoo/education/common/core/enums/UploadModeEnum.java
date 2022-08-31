package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上传方式
 *
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum UploadModeEnum {

    LOCAL(1, "本地", "local"), MinIO(2, "MinIO", "minio");

    private Integer code;

    private String desc;

    private String mode;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static UploadModeEnum getByCode(Integer code) {
        for (UploadModeEnum val : UploadModeEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }
}
