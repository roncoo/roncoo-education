package com.roncoo.education.common.video.impl.priyun.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询视频信息
 *
 * @author LYQ
 */
@Data
public class PrivateYunInfoResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 存储使用空间，单位：KB
     */
    private Long storage;

    /**
     * 视频总数量
     */
    private Long count;
}
