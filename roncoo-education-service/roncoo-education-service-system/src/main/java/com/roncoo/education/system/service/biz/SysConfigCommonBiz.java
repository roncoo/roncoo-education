package com.roncoo.education.system.service.biz;

import com.roncoo.education.common.core.base.Constants;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.tools.RsaUtil;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;
import com.roncoo.education.system.feign.interfaces.vo.LoginConfig;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysConfigCommonBiz extends BaseBiz {

    @NotNull
    private final SysConfigDao dao;

    public <T> T getSysConfig(Class<T> clazz) {
        List<SysConfig> sysConfigs = dao.listByExample(new SysConfigExample());
        Map<String, String> map = sysConfigs.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
        return BeanUtil.objToBean(map, clazz);
    }

    public String decrypt(String password) {
        String privateKey = cacheRedis.get(Constants.RedisPre.PRIVATEKEY);
        if (!StringUtils.hasText(privateKey)) {
            privateKey = getSysConfig(LoginConfig.class).getRsaLoginPrivateKey();
            cacheRedis.set(Constants.RedisPre.PRIVATEKEY, privateKey, 1, TimeUnit.DAYS);
        }
        return RsaUtil.decrypt(password, privateKey);
    }
}
