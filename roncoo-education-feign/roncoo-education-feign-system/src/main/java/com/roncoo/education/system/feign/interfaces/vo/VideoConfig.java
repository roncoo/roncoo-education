package com.roncoo.education.system.feign.interfaces.vo;

import com.roncoo.education.common.video.req.VideoConfigReq;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 点播配置
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class VideoConfig extends VideoConfigReq implements Serializable {

    private static final long serialVersionUID = 1L;

}
