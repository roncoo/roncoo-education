package com.roncoo.education.system.service.api;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.RandomUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.core.tools.EnumUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.service.api.req.ApiEnumReq;
import com.roncoo.education.system.service.api.resp.ApiVerCodeResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 获取枚举信息
 *
 * @author liaoh
 */
@Slf4j
@Api(tags = "api-枚举接口")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/system/api/common")
public class ApiCommonController {

    @NotNull
    private final CacheRedis cacheRedis;

    private static final String DEFAULT_ENUM_NAME = "name";
    private static final String DEFAULT_PREFIX = "com.roncoo.education.common.core.enums.";

    @ApiOperation(value = "枚举通用接口", notes = "返回枚举信息")
    @RequestMapping(value = "/enum", method = RequestMethod.POST)
    public Result<ArrayList<?>> getEnumInfo(@RequestBody ApiEnumReq req) {
        String className = DEFAULT_PREFIX + req.getEnumName();
        try {
            Class<?> clazz = Class.forName(className);
            return Result.success(new ArrayList<>(Objects.requireNonNull(EnumUtil.toList(clazz, DEFAULT_ENUM_NAME))));
        } catch (ClassNotFoundException e) {
            log.error("获取枚举失败, className={}", className, e);
            return Result.error("获取枚举失败");
        }
    }

    @ApiOperation(value = "获取图片验证码", notes = "获取图片验证码")
    @GetMapping(value = "/code")
    public Result<ApiVerCodeResp> getVerCode() {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, new RandomGenerator(RandomUtil.BASE_NUMBER, 4), 18);
        ApiVerCodeResp resp = new ApiVerCodeResp(String.valueOf(IdWorker.getId()), captcha.getImageBase64Data());
        // 存入缓存，过期时间为5分钟
        cacheRedis.set(Constants.RedisPre.VER_CODE + resp.getVerToken(), captcha.getCode().toLowerCase(), 5, TimeUnit.MINUTES);
        return Result.success(resp);
    }

}
