package com.roncoo.education.common.polyv.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 保利威视上传附属信息
 *
 * @author LYQ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolyvUploadState implements Serializable {

    /**
     * 类型(1：素材、2:转存任务)
     */
    private Integer type;

    /**
     * 资源ID
     */
    private Long relationId;
}
