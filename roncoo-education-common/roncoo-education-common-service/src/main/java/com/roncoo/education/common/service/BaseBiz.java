/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.service;

import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 基础类
 *
 * @author wujing
 */
@Component
public class BaseBiz extends Base {

    @Autowired
    protected CacheRedis cacheRedis;

}
