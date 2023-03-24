package com.roncoo.education.common.video.impl.priyun.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 获取token返回信息
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class PrivateYunPlayTokenRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String videoVid;

    private String token;

    private PrivateYunVideoClarityResp videoClarityResp;

}
