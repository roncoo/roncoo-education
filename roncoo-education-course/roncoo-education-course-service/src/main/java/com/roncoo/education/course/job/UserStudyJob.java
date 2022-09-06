package com.roncoo.education.course.job;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.service.auth.req.AuthUserStudyReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author fengyw
 */
@Component
public class UserStudyJob {
    @Autowired
    private UserStudyDao userStudyDao;

    @Autowired
    private CacheRedis cacheRedis;

    /**
     * 每分钟执行一次
     */
    @Scheduled(fixedRate = 60000)
    public void progress() {
        // 处理学习进度
        Set<String> keys = cacheRedis.getStringRedisTemplate().keys(Constants.RedisPre.PROGRESS + "*");
        if (CollUtil.isNotEmpty(keys)) {
            for (String key : keys) {
                if (cacheRedis.getStringRedisTemplate().getExpire(key, TimeUnit.MINUTES) < 59) {
                    // 默认过期时间为60分钟，若剩余时间小于59分，则处理
                    AuthUserStudyReq req = cacheRedis.getByJson(key, AuthUserStudyReq.class);
                    UserStudy userStudy = userStudyDao.getById(req.getStudyId());
                    userStudy.setProgress(req.getCurrentDuration().divide(req.getTotalDuration()));
                    // 清楚缓存
                    cacheRedis.delete(Constants.RedisPre.USER_STUDY + req.getStudyId());
                    cacheRedis.delete(key);
                }
            }
        }
    }

}
