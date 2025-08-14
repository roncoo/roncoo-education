package com.roncoo.education.common.video.impl.polyv.vod.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PolyvVodUploadConfig {

    private String userid;

    private Long ptime;

    private String sign;

    private String hash;

    private String categoryId;
}
