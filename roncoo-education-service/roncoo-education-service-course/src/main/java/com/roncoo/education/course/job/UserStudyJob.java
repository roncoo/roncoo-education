package com.roncoo.education.course.job;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Constants;
import com.roncoo.education.common.core.enums.ResourceTypeEnum;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.service.auth.req.AuthUserStudyReq;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author fengyw
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserStudyJob {

    @NotNull
    private final CacheRedis cacheRedis;

    @NotNull
    private final UserStudyDao userStudyDao;


    /**
     * 建议：每10秒执行一次
     */
    @XxlJob("userStudyJobHandler")
    public void progress() {
        // 处理学习进度
        Set<String> keys = cacheRedis.getStringRedisTemplate().keys(Constants.RedisPre.PROGRESS + "*");
        if (CollUtil.isNotEmpty(keys)) {
            for (String key : keys) {
                if (cacheRedis.getStringRedisTemplate().getExpire(key, TimeUnit.MINUTES) < 1430) {
                    // 默认过期时间为1天，1440分钟（若超过10分钟不学习，则处理）
                    AuthUserStudyReq req = cacheRedis.get(key, AuthUserStudyReq.class);
                    UserStudy userStudy = userStudyDao.getById(req.getStudyId());
                    if (ResourceTypeEnum.VIDEO.getCode().equals(req.getResourceType()) || ResourceTypeEnum.AUDIO.getCode().equals(req.getResourceType())) {
                        userStudy.setProgress(req.getCurrentDuration().divide(new BigDecimal(req.getTotalDuration()), RoundingMode.CEILING).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
                        userStudy.setCurrentDuration(req.getCurrentDuration().intValue());
                    } else if (ResourceTypeEnum.DOC.getCode().equals(req.getResourceType())) {
                        userStudy.setProgress(BigDecimal.valueOf(req.getCurrentPage()).divide(BigDecimal.valueOf(req.getTotalPage())).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
                        userStudy.setCurrentPage(req.getCurrentPage());
                    }
                    userStudyDao.updateById(userStudy);
                    // 清楚缓存
                    cacheRedis.delete(Constants.RedisPre.USER_STUDY + req.getStudyId());
                    cacheRedis.delete(key);
                }
            }
        }
        XxlJobHelper.handleSuccess("完成");
    }

}
