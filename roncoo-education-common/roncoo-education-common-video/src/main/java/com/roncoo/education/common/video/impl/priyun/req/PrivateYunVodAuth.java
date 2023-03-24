package com.roncoo.education.common.video.impl.priyun.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class PrivateYunVodAuth {

    private String videoVid;

    private String viewId;

    private String state;
}
