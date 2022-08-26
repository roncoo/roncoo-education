/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.base;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础类
 *
 * @author wujing
 */
public class Base {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public void log(Object obj) {
        log.info(JSONUtil.toJsonPrettyStr(obj));
    }
}
