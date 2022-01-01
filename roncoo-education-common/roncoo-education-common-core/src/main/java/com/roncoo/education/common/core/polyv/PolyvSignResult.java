/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.polyv;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课时信息获取sign值
 *
 * @author forest
 */
@Data
@Accessors(chain = true)
public class PolyvSignResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ts;

    private String sign;

    private String token;

}
